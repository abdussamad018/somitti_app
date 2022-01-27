package com.example.ibbl.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibbl.Adapter.LoanShow_adaptar;
import com.example.ibbl.Model.LoanApply;
import com.example.ibbl.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoanApplicationShow extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    List<LoanApply> userList;
    String UserId;
    LoanShow_adaptar adpater;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = findViewById(R.id.LoanPendingToolbar);
        setContentView(R.layout.activity_loan_application_show);
        recyclerView = findViewById(R.id.LoanShowRecyView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        userList = new ArrayList<LoanApply>();
        setSupportActionBar(toolbar);
        AllLoanApplication();
    }

    private void AllLoanApplication() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        UserId = firebaseAuth.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("LoanApplication");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    LoanApply loanApply = snapshot.getValue(LoanApply.class);
                    userList.add(loanApply);
                }
                adpater = new LoanShow_adaptar(userList, getApplicationContext());
                recyclerView.setAdapter(adpater);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
