package com.example.ibbl;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ibbl.Activity.DesboardAcitvity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Login;
    private EditText UserName, Password;
    private TextView textView, forgot;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    String email;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = (Button) findViewById(R.id.btnLogin);
        UserName = (EditText) findViewById(R.id.txtUser);
        Password = (EditText) findViewById(R.id.txtPass);
        textView = (TextView) findViewById(R.id.text);
        forgot = findViewById(R.id.forgotId);
        progressBar = findViewById(R.id.progressId);
        Login.setOnClickListener(this);
        forgot.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
UserPermission();

    }

    private void UserPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.INTERNET,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnLogin: {
                UserLogin();
                break;
            }
            case R.id.forgotId: {

                UserForgot();
                break;
            }
        }
    }

    private void UserForgot() {
        final EditText resetMail = new EditText(forgot.getContext());
        final AlertDialog.Builder PasswordResetDialog = new AlertDialog.Builder(forgot.getContext());
        PasswordResetDialog.setTitle("Reset Password ?");
        PasswordResetDialog.setMessage("Enter Your Mail To Receive Reset Link");
        PasswordResetDialog.setView(resetMail);
        PasswordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email = resetMail.getText().toString();
                mAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Reset Link Sent To Your Email", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Reset Link is not Sent" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        PasswordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        PasswordResetDialog.create().show();

    }

    private void UserLogin() {
        email = UserName.getText().toString().trim();
        password = Password.getText().toString().trim();

        if (email.isEmpty()) {
            UserName.setError("Please Enter Your UserName");
            UserName.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            Password.setError("Please Enter Your Password");
            Password.requestFocus();
            return;
        }
        if (password.length() < 6) {
            Password.setError("Please Enter Minimum 6 digit");
            Password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {


                    finish();


                    Intent intent1 = new Intent(getApplicationContext(), DesboardAcitvity.class);
                    startActivity(intent1);
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();


                } else {

                    Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}



