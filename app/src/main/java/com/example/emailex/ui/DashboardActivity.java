package com.example.emailex.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.emailex.R;
import com.example.emailex.Utils.Loader;
import com.example.emailex.firebase.Constants;
import com.example.emailex.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardActivity extends AppCompatActivity {
    Button signoutBtn;
    Toolbar toolbar3;
    Loader loader;
    TextView name, age, mobile, address;
    Button click, click2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        click = findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ListActivity.class));
            }
        });
        click2 = findViewById(R.id.click2);
        click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, AccountActivity.class));
            }
        });

        init();
        signout_listener();
        FinalViewById();


        if (FirebaseAuth.getInstance().getUid() != null) {
            FirebaseDatabase.getInstance().getReference()
                    .child(Constants.Users.key)
                    .child(FirebaseAuth.getInstance().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            // dataSnapshot.getValue(Users.class);

                            name.setText(dataSnapshot.child(Constants.Users.name).getValue().toString());
                            age.setText(dataSnapshot.child(Constants.Users.age).getValue().toString());
                            mobile.setText(dataSnapshot.child(Constants.Users.mobile).getValue().toString());
                            address.setText(dataSnapshot.child(Constants.Users.address).getValue().toString());


                            Log.i("fgvyjdf", "onDataChange: " + dataSnapshot.toString());

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }

    }

    private void FinalViewById() {
        name = findViewById(R.id.getTextName);
        age = findViewById(R.id.getTextAge);
        mobile = findViewById(R.id.getTextMobile);
        address = findViewById(R.id.getTextAddress);

    }

    private void signout_listener() {
        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loader.show();
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(DashboardActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                loader.show();
                finish();
            }
        });
    }

    private void init() {
        toolbar3 = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        getSupportActionBar().setTitle("Dashboard");
        signoutBtn = findViewById(R.id.signoutBtn);
        loader = new Loader(this);
    }
}
