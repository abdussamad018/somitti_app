package com.example.ibbl.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.ibbl.Model.User;
import com.example.ibbl.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Deposite extends AppCompatActivity {
    CircleImageView profile_pic;
    TextView UserName;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Intent intent;
    List<User> DueCollectionList;
    String UserId;
    EditText taka;
    Button taka_joma;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposite);
        profile_pic=findViewById(R.id.Profile_img);
        UserName=findViewById(R.id.UserNameTextViewId);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("MyMember");
        intent=getIntent();
        UserId=intent.getStringExtra("UserId");
        taka=findViewById(R.id.Taka);
        taka_joma=findViewById(R.id.takaJoma);
        toolbar=findViewById(R.id.MyToolberId);
        setSupportActionBar(toolbar);

        databaseReference.child(UserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                UserName.setText(user.getName());
                if (user.getProfile_img().equals("noimg")){
                    profile_pic.setImageResource(R.drawable.img_1);
                }else {
                    Glide.with(getApplicationContext()).load(user.getProfile_img()).into(profile_pic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
