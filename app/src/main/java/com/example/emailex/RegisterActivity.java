package com.example.emailex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class RegisterActivity extends AppCompatActivity {

    Button signupBtn;
    TextView login;
    EditText editTextEmail2, editTextPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.activity_lottie))
                .setExpanded(false)
                .setContentBackgroundResource(Color.TRANSPARENT)
                .setGravity(Gravity.CENTER)
                .create();
        LinearLayout layout = (LinearLayout) dialog.getHolderView();

        signupBtn = findViewById(R.id.signupBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextEmail2 = findViewById(R.id.editTextEmail2);
                editTextPassword2 = findViewById(R.id.editTextPassword2);

                String email2, password2, emailPattern2;

                email2 = editTextEmail2.getText().toString();
                password2 = editTextPassword2.getText().toString();
                emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

                if (TextUtils.isEmpty(email2)) {
                    Toast.makeText(RegisterActivity.this, "Enter Email address", Toast.LENGTH_SHORT).show();
                } else if (!email2.matches(emailPattern2)) {
                    Toast.makeText(RegisterActivity.this, "Enter valid Email address", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email2)) {
                    Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                } else if (password2.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Enter atleast Six characters", Toast.LENGTH_SHORT).show();
                } else {

                }
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextEmail2.getText().toString(), editTextPassword2.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "Successfully Sign Up", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                dialog.show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "failed ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        login = (TextView) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
