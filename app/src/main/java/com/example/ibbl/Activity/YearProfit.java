package com.example.ibbl.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ibbl.Model.User;
import com.example.ibbl.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class YearProfit extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String UserId;
    TextView Year_profit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Year_profit=findViewById(R.id.YearProfitId);
        mAuth=FirebaseAuth.getInstance();
        UserId=mAuth.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference("MyMember");

        databaseReference.child(UserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                Year_profit.setText("আপনার বর্তমান লাভ : " +user.getProfit()+ " টাকা");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
