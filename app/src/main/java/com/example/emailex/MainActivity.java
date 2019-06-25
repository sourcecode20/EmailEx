package com.example.emailex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button login;
    TextView signup;
    EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FirebaseAuth.getInstance().createUserWithEmailAndPassword(editText1.getText().toString(),editText2.getText().toString())
//                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        Log.i("success", "onComplete: "+FirebaseAuth.getInstance().getCurrentUser().getEmail()+" "+
//                                FirebaseAuth.getInstance().getCurrentUser().getUid());
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.i("success", "onFailure: "+e.toString());
//            }
//        });


        login = (Button) findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1 = findViewById(R.id.editText1);
                editText2 = findViewById(R.id.editText2);
                FirebaseAuth.getInstance().signInWithEmailAndPassword(editText1.getText().toString(), editText2.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Log.i("success", "onSuccess: " + FirebaseAuth.getInstance().getCurrentUser().getEmail() + " " +
                                        FirebaseAuth.getInstance().getCurrentUser().getUid());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("success", "onFailure: " + e.toString());
                    }
                });
                startActivity(new Intent(MainActivity.this, Dashboard.class));
            }
        });

        signup = (TextView) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });


    }
}
