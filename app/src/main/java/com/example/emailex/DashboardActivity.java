package com.example.emailex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.emailex.Utils.Loader;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {
    Button signoutBtn;
    Toolbar toolbar3;
    Loader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();
        signout_listener();
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        signoutBtn = findViewById(R.id.signoutBtn);
        loader = new Loader(this);
    }
}
