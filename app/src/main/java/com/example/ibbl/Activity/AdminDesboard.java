package com.example.ibbl.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.ibbl.R;

public class AdminDesboard extends AppCompatActivity implements View.OnClickListener {
    CardView addMember, dueColection, dueList, memberUpdate, loanApply, comunication;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_desboard);
        addMember = findViewById(R.id.AdminAddMember);
        dueColection = findViewById(R.id.AdminDueColection);
        dueList = findViewById(R.id.AdminDueList);
        memberUpdate = findViewById(R.id.AdminMemberUpdate);
        loanApply = findViewById(R.id.AdminLoanApply);
        toolbar = findViewById(R.id.AdminPanelToolbar);
        comunication = findViewById(R.id.AdminContract);
        addMember.setOnClickListener(this);
        dueColection.setOnClickListener(this);
        dueList.setOnClickListener(this);
        memberUpdate.setOnClickListener(this);
        loanApply.setOnClickListener(this);
        comunication.setOnClickListener(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.AdminAddMember: {
                Intent intent = new Intent(getApplicationContext(), MemberAdd.class);
                startActivity(intent);
                break;
            }
            case R.id.AdminDueColection: {
                Intent intent = new Intent(getApplicationContext(), DueColection.class);
                startActivity(intent);
                break;
            }

            case R.id.AdminLoanApply: {
                Intent intent = new Intent(getApplicationContext(), LoanApplicationShow.class);
                startActivity(intent);
                break;
            }
            case R.id.AdminContract: {
                Intent intent = new Intent(getApplicationContext(), Comunication.class);
                startActivity(intent);
                break;
            }
            case R.id.AdminDueList: {
                Intent intent = new Intent(getApplicationContext(), DueList.class);
                startActivity(intent);
                break;
            }

        }

    }
}
