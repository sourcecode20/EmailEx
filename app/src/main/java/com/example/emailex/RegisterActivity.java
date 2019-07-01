package com.example.emailex;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.emailex.Utils.Loader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    Button signupBtn;
    TextView login;
    EditText editTextEmail2, editTextPassword2;
    Toolbar toolbar1;
    Loader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        signup_listener();
        login_listener();

    }
    private void login_listener() {
        login = (TextView) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void signup_listener() {

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loader.show();
                editTextEmail2 = findViewById(R.id.editTextEmail2);
                editTextPassword2 = findViewById(R.id.editTextPassword2);

                String email2, password2;
                email2 = editTextEmail2.getText().toString();
                password2 = editTextPassword2.getText().toString();

                if (validation(email2, password2)) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextEmail2.getText().toString(), editTextPassword2.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(RegisterActivity.this, "Successfully Sign Up", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                } else
                    loader.dismis();
            }
        });
    }

    private void init() {
        toolbar1 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loader=new Loader(this);
        signupBtn = findViewById(R.id.signupBtn);

    }

    private boolean validation(String email2, String password2) {
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        if (TextUtils.isEmpty(email2)) {
            Toast.makeText(RegisterActivity.this, "Enter Email address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!email2.matches(emailPattern2)) {
            Toast.makeText(RegisterActivity.this, "Enter valid Email address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(password2)) {
            Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password2.length() < 6) {
            Toast.makeText(RegisterActivity.this, "Enter atleast Six characters", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}
