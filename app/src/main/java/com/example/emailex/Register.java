package com.example.emailex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText editText3, editText4;
    Button signBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signBtn = findViewById(R.id.signBtn);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText3 = findViewById(R.id.editText3);
                editText4 = findViewById(R.id.editText4);

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(editText3.getText().toString(), editText4.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.i("success", "onComplete: " + FirebaseAuth.getInstance().getCurrentUser().getEmail() + " " +
                                        FirebaseAuth.getInstance().getCurrentUser().getUid());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Failure", "onFailure: " + e.toString());
                    }
                });

                startActivity(new Intent(Register.this, MainActivity.class));
                Toast.makeText(Register.this,"Successful Sign Up",Toast.LENGTH_SHORT).show();

//                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                Log.i("success", "onComplete: "+FirebaseAuth.getInstance().getCurrentUser().getEmail()+" "+
//                                        FirebaseAuth.getInstance().getCurrentUser().getUid());
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.i("success", "onFailure: "+e.toString());
//                    }
//                });

            }
        });


    }
}
