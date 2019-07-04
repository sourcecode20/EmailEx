package com.example.emailex.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.emailex.R;
import com.example.emailex.Utils.Loader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity {
    Button signoutBtn;
    Toolbar toolbar3;
    Loader loader;
    TextView name,age,mobile,address;
    ArrayList<String> list = new ArrayList<>();
    DatabaseReference databaseReference;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();
        signout_listener();






//        FirebaseDatabase.getInstance().getReference()
//                .child("Users")
//                .child(FirebaseAuth.getInstance().getUid())
//                .child("name")
//                .setValue("dshgvchs")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Log.i("jsdvchgsd", "onComplete: ");
//
//                    }
//                });
//
//
//
//
//
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("name","scgd");
//        map.put("age",18);
//        map.put("add","sdhcghsdg");
//
//
//        FirebaseDatabase.getInstance().getReference()
//                .child("Users")
//                .child(FirebaseAuth.getInstance().getUid())
//                .updateChildren(map)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Log.i("jsdvchgsd", "onComplete: ");
//                    }
//                });
//


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
