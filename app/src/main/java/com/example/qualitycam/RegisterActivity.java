package com.example.qualitycam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    Button btn_register;
    TextView txt_login;

    EditText rg_email, rg_password, rg_firstName, rg_lastName;
    private FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    @Override
    public void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        if (firebaseUser != null) {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register = findViewById(R.id.btn_register);
        txt_login = findViewById(R.id.txt_login);

        rg_email = findViewById(R.id.rg_email);
        rg_password = findViewById(R.id.rg_password);
        rg_firstName = findViewById(R.id.rg_firstName);
        rg_lastName = findViewById(R.id.rg_lastName);

        String email = rg_email.getText().toString().trim();
        String password = rg_password.getText().toString().trim();
        String firstName = rg_firstName.getText().toString().trim();
        String lastName = rg_lastName.getText().toString().trim();





        mAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rg_firstName.getText().toString().trim().isEmpty()){
                    rg_firstName.setError("Enter First Name");
                    rg_firstName.requestFocus();
                    return;
                }

                if (rg_lastName.getText().toString().trim().isEmpty()){
                    rg_lastName.setError("Enter Last Name");
                    rg_lastName.requestFocus();
                    return;
                }

                if (rg_email.getText().toString().trim().isEmpty()){
                    rg_email.setError("Please Enter Email");
                    rg_email.requestFocus();
                    return;
                }else if (!(Patterns.EMAIL_ADDRESS.matcher(rg_email.getText().toString().trim()).matches())){
                    rg_email.setError("Please Correct Email");
                    rg_email.requestFocus();
                    return;
                }

                if (rg_password.getText().toString().trim().isEmpty()){
                    rg_password.setError("Please Enter Password");
                    rg_password.requestFocus();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(rg_email.getText().toString().trim(),rg_password.getText().toString().trim() ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        UserModel userModel = new UserModel(rg_firstName.getText().toString().trim(), rg_lastName.getText().toString().trim(), rg_email.getText().toString().trim(), rg_password.getText().toString().trim());
                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(RegisterActivity.this, IntroActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(RegisterActivity.this, "User Not Created..", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(RegisterActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });

            }
        });

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }




}