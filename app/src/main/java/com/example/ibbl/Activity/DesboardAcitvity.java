package com.example.ibbl.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.ibbl.MainActivity;
import com.example.ibbl.Model.User;
import com.example.ibbl.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class DesboardAcitvity extends AppCompatActivity implements View.OnClickListener {
    private CardView Balance, Report, Statment, profit, Application, Contract;
    private TextView textView, OrgName;
    CircleImageView Profile_img;
    TextView UserName;
    String UserId;

    DatabaseReference databaseReference;
    public static final int MY_REQUEST_CODE = 1;
    StorageReference storageReference;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desboard_acitvity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.MyToolberId);
        Profile_img = (CircleImageView) findViewById(R.id.Profile_img);
        UserName = (TextView) findViewById(R.id.UserNameTextViewId);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("MyMember").child(firebaseUser.getUid());
        storageReference = FirebaseStorage.getInstance().getReference("Upload");
        Balance = (CardView) findViewById(R.id.Balance);
        Report = (CardView) findViewById(R.id.TotalDUEID);
        Statment = (CardView) findViewById(R.id.YearPRofitID);
        profit = (CardView) findViewById(R.id.profitID);
        Application = (CardView) findViewById(R.id.Apply);
        Contract = findViewById(R.id.Contract);
        textView = (TextView) findViewById(R.id.TextBalance);
        OrgName = (TextView) findViewById(R.id.OrgName);
        Balance.setOnClickListener(this);
        Report.setOnClickListener(this);
        Statment.setOnClickListener(this);
        profit.setOnClickListener(this);
        Application.setOnClickListener(this);
        Contract.setOnClickListener(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        OrgName.setText(R.string.Org);


        databaseReference = FirebaseDatabase.getInstance().getReference("MyMember").child(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                UserName.setText(user.getName());
                if (user.getProfile_img().equals("noimg")) {
                    Profile_img.setImageResource(R.drawable.logo);
                } else {
                    Glide.with(DesboardAcitvity.this).load(user.getProfile_img()).into(Profile_img);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUpload();
            }
        });
    }

    private void imageUpload() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Uri uri = data.getData();

                    final StorageReference MyUpload = storageReference.child(UserName.getText().toString() + System.currentTimeMillis());
                    MyUpload.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(DesboardAcitvity.this, "Uploaded", Toast.LENGTH_SHORT).show();

                            MyUpload.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    Uri myuri = task.getResult();
                                    String myurl = myuri.toString();
                                    HashMap<String, Object> uploadref = new HashMap<>();
                                    uploadref.put("Profile_img", myurl);
                                    databaseReference.updateChildren(uploadref).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(DesboardAcitvity.this, "Profile Picture Updated", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    });
                }

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulayout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.SignOutMenuId) {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "SignOut Success", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Balance: {
                Intent intent = new Intent(DesboardAcitvity.this, BalanceActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.TotalDUEID: {
                Intent intent = new Intent(DesboardAcitvity.this, Due.class);
                startActivity(intent);
                break;
            }
            case R.id.YearPRofitID: {
                Intent intent = new Intent(DesboardAcitvity.this, YearProfit.class);
                startActivity(intent);
                break;
            }
            case R.id.profitID: {
                Intent intent = new Intent(DesboardAcitvity.this, ProfitActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.SignOutMenuId: {
                Intent intent = new Intent(DesboardAcitvity.this, MainActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.Apply: {
                Intent intent = new Intent(DesboardAcitvity.this, Application.class);
                startActivity(intent);
                break;
            }

            case R.id.Contract: {
                Intent intent = new Intent(DesboardAcitvity.this, Comunication.class);
                startActivity(intent);
                break;
            }
        }
    }
}
