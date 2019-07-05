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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.emailex.R;
import com.example.emailex.Utils.Loader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity {
    Button signoutBtn;
    Toolbar toolbar3;
    Loader loader;
    TextView name,age,mobile,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();
        signout_listener();
        FinalViewById();
//        Bundle bundle;
//        bundle=getIntent().getExtras();
//        name.setText(bundle.getCharSequence("name"));
//        age.setText(bundle.getCharSequence("age"));
//        mobile.setText(bundle.getCharSequence("mobile"));
//        address.setText(bundle.getCharSequence("address"));

        if (FirebaseAuth.getInstance().getUid() != null) {
            FirebaseDatabase.getInstance().getReference()
                    .child("Users")
                    .child(FirebaseAuth.getInstance().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            name.setText(dataSnapshot.child("Name").getValue().toString());
                            age.setText(dataSnapshot.child("Age").getValue().toString());
                            mobile.setText(dataSnapshot.child("Mobile").getValue().toString());
                            address.setText(dataSnapshot.child("Address").getValue().toString());
                            Log.i("fgvyjdf", "onDataChange: "+dataSnapshot.toString());



                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }


//        FirebaseDatabase.getInstance().getReference()
//                .child("Users")
//                .child(FirebaseAuth.getInstance().getUid())
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        Log.i("fgvyjdf", "addValueEventListener: "+dataSnapshot.child("Name").getValue().toString());
//                        Log.i("fgvyjdf", "addValueEventListener: "+dataSnapshot.toString());
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });

    }

    private void FinalViewById() {
        name=findViewById(R.id.getTextName);
        age=findViewById(R.id.getTextAge);
        mobile=findViewById(R.id.getTextMobile);
        address=findViewById(R.id.getTextAddress);

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
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        signoutBtn = findViewById(R.id.signoutBtn);
        loader = new Loader(this);
    }
}
