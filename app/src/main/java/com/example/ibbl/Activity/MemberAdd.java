package com.example.ibbl.Activity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ibbl.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MemberAdd extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private DatabaseReference databaseReference;
    private EditText email, password, name, Total_tk, Total_due, Total_profit, profit, deposite;
    private Button AddMemberBtn;
    private String userId;
    private String email_str;
    private String name_str;
    private String Total_tk_str;
    private String Due_tk;
    private String deposite_str;
    private String profit_str;
    private String total_profit_str;
    private String password_str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_add);
        databaseReference = FirebaseDatabase.getInstance().getReference("MyMember");

        email = findViewById(R.id.AddtxtUser);
        password = findViewById(R.id.addtxtPass);
        name = findViewById(R.id.addtxtName);
        Total_tk = findViewById(R.id.txtTotalTKId);
        Total_due = findViewById(R.id.txtTotalDueId);
        Total_profit = findViewById(R.id.TotalProfitID);
        profit = findViewById(R.id.addProfitID);
        deposite = findViewById(R.id.DepositeID);
        AddMemberBtn = findViewById(R.id.btnAdd);
        progressBar = findViewById(R.id.MemberProgressId);
        mAuth = FirebaseAuth.getInstance();
        AddMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMembers();

            }
        });

    }

    private void addMembers() {
        email_str = email.getText().toString().trim();
        password_str = password.getText().toString().trim();
        name_str = name.getText().toString();
        Total_tk_str = Total_tk.getText().toString();
        Due_tk = Total_due.getText().toString();
        total_profit_str = Total_profit.getText().toString();
        profit_str = profit.getText().toString();
        deposite_str = deposite.getText().toString();

        if (name_str.isEmpty()) {
            name.setError("Please Enter Your Name");
            name.requestFocus();
            return;
        }

        if (email_str.isEmpty()) {
            email.setError("Please Enter Your Email");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email_str).matches()) {
            email.setError("Please Enter Valid Email");
            email.requestFocus();
            return;
        }
        if (password_str.isEmpty()) {
            password.setError("Please Enter Password");
            password.requestFocus();
            return;
        }
        if (password_str.length() < 6) {
            password.setError("Please Enter Atleast 6 Digit ");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email_str, password_str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                     userId = mAuth.getUid();
                    HashMap<String, String> Myref = new HashMap<>();
                    Myref.put("ID", userId);
                    Myref.put("Email", email_str);
                    Myref.put("Password", password_str);
                    Myref.put("Name", name_str);
                    Myref.put("Total_tk", Total_tk_str);
                    Myref.put("Total_Due", Due_tk);
                    Myref.put("Total_profit", total_profit_str);
                    Myref.put("Profit", profit_str);
                    Myref.put("Deposite", deposite_str);
                    Myref.put("Profile_img", "noimg");
                    databaseReference.child(userId).setValue(Myref).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MemberAdd.this, "Account Create an Sucess", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MemberAdd.this, "Error", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });


                }

            }
        });

    }

}
