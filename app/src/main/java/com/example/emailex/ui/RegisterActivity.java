package com.example.emailex.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.emailex.R;
import com.example.emailex.Utils.Loader;
import com.example.emailex.firebasse.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    Button signupBtn;
    TextView login;
    EditText editTextEmail2, editTextPassword2, editTextName, editTextAge, editTextMobile, editTextAddress;
    Toolbar toolbar2;
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
                editTextName = findViewById(R.id.editTextName);
                editTextAge = findViewById(R.id.editTextAge);
                editTextMobile = findViewById(R.id.editTextMobile);
                editTextAddress = findViewById(R.id.editTextAddress);


                final String email2, password2, name, age, mobile, address;
                email2 = editTextEmail2.getText().toString();
                password2 = editTextPassword2.getText().toString();
                name = editTextName.getText().toString();
                age = editTextAge.getText().toString();
                mobile = editTextMobile.getText().toString();
                address = editTextAddress.getText().toString();
                Log.i("sdbchjsbdf", "signup_listener: ");

                if (validation(email2, password2, name, age, mobile, address)) {
                    Log.i("sdbchjsbdf", "validation: ");

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextEmail2.getText().toString(), editTextPassword2.getText().toString())
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    Log.i("sdbchjsbdf", "createUserWithEmailAndPassword: ");


                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put(Constants.Users.name, editTextName.getText().toString());
                                    map.put(Constants.Users.age, editTextAge.getText().toString());
                                    map.put(Constants.Users.mobile, editTextMobile.getText().toString());
                                    map.put(Constants.Users.address, editTextAddress.getText().toString());

                                    if (FirebaseAuth.getInstance().getUid() != null)
                                        FirebaseDatabase.getInstance().getReference()
                                                .child(Constants.Users.key)
                                                .child(FirebaseAuth.getInstance().getUid())
                                                .updateChildren(map)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Log.i("sdbchjsbdf", "addOnCompleteListener: ");

                                                        loader.dismis();

                                                        Toast.makeText(RegisterActivity.this, "Successfully Sign Up", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));
                                                        finish();

                                                    }
                                                });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("sdbchjsbdf", "onFailure : ");
                        }
                    });
                } else
                    loader.dismis();
            }
        });
    }

    private void init() {
        toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loader = new Loader(this);
        signupBtn = findViewById(R.id.signupBtn);

    }

    private boolean validation(String email2, String password2, String name, String age, String mobile, String address) {
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        String agePattern = "^(0|[1-9][0-9]*)$";
        String mobilePattern = "(0/91)?[7-9][0-9]{9}";

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(RegisterActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(age)) {
            Toast.makeText(RegisterActivity.this, "Enter Age", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!age.matches(agePattern)) {
            Toast.makeText(RegisterActivity.this, "Enter Correct age", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(mobile)) {
            Toast.makeText(RegisterActivity.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!mobile.matches(mobilePattern)) {
            Toast.makeText(RegisterActivity.this, "Enter Correct Mobile number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(address)) {
            Toast.makeText(RegisterActivity.this, "Enter Address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(email2)) {
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
