package com.example.emailex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
        Button signoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        signoutBtn=findViewById(R.id.signoutBtn);
        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this,MainActivity.class));
                Toast.makeText(Dashboard.this,"Sign Out",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
