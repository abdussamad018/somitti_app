package com.example.ibbl.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ibbl.Model.LoanApply;
import com.example.ibbl.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Application extends AppCompatActivity {
    EditText UserName, Taka, Din, somossa;
    Button Send;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String UserId;
    String taka;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    TextView Status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        UserName = findViewById(R.id.ApplyNameID);
        Taka = findViewById(R.id.ApplyTakaID);
        Din = findViewById(R.id.ApplySomoyID);
        somossa = findViewById(R.id.ApplyMessageID);
        progressBar = findViewById(R.id.progrsssssssId);
        databaseReference = FirebaseDatabase.getInstance().getReference("LoanApplication");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        relativeLayout = findViewById(R.id.Relativ);
        linearLayout = findViewById(R.id.liniyar);
        Status = findViewById(R.id.StatusID);
        Send = findViewById(R.id.Send);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = UserName.getText().toString();
                    taka = Taka.getText().toString();
                    String time = Din.getText().toString();
                    String Somossa_Str = somossa.getText().toString();

                    if (name.isEmpty()) {
                        UserName.setError(getString(R.string.name).toString());
                        UserName.requestFocus();
                    }


                    if (taka.isEmpty()) {
                        Taka.setError(getString(R.string.takar_proriman).toString());
                        Taka.requestFocus();

                    }

                    if (time.isEmpty()) {
                        Din.setError(getString(R.string.Somoy));
                        Din.requestFocus();
                    }

                    if (Somossa_Str.isEmpty()) {
                        somossa.setError(getString(R.string.somossa));
                        somossa.requestFocus();
                    }


                    progressBar.setVisibility(View.VISIBLE);
                    UserId = firebaseAuth.getUid();
                    HashMap<String, String> myref = new HashMap<>();
                    myref.put("ID", UserId);
                    myref.put("UserName", name);
                    myref.put("Taka", taka);
                    myref.put("Time", time);
                    myref.put("Message", Somossa_Str);
                    myref.put("Status", "Pending");

                    databaseReference.child(UserId).setValue(myref).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);

                                Toast.makeText(getApplicationContext(), "আপনার আবেদন সফল হয়েছে", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "আপনার আবেদন সফল হয়নি", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(Application.this, "Data Entry korun", Toast.LENGTH_SHORT).show();
                }


            }
        });

        DatabaseReference laodDatabase = FirebaseDatabase.getInstance().getReference("LoanApplication").child(firebaseUser.getUid());
        laodDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                LoanApply loanApply = dataSnapshot.getValue(LoanApply.class);
                Status.setText(loanApply.getStatus());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
