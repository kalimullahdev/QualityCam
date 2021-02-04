package com.example.qualitycam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    TextView txt_register;

    EditText lg_email, lg_password;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        txt_register = findViewById(R.id.txt_register);
        lg_email = findViewById(R.id.lg_email);
        lg_password = findViewById(R.id.lg_password);

        mAuth = FirebaseAuth.getInstance();

        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lg_email.getText().toString().trim().isEmpty()){
                    lg_email.setError("Please Enter Email");
                    lg_email.requestFocus();
                    return;
                }else if (!(Patterns.EMAIL_ADDRESS.matcher(lg_email.getText().toString().trim()).matches())){
                    lg_email.setError("Please Correct Email");
                    lg_email.requestFocus();
                    return;
                }

                if (lg_password.getText().toString().trim().isEmpty()){
                    lg_password.setError("Please Enter Password");
                    lg_password.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(lg_email.getText().toString().trim(), lg_password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(LoginActivity.this, IntroActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


//                Toast.makeText(LoginActivity.this, "saf  "+lg_email.getText().toString().trim()+" "+ lg_password.getText().toString().trim(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}