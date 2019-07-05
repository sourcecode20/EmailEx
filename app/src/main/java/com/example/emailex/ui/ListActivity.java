package com.example.emailex.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.emailex.R;
import com.example.emailex.adapter.ListAdapter;
import com.example.emailex.firebasse.Constants;
import com.example.emailex.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Users> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // recyclerView.setAdapter(new ListAdapter(users,this));

        getData();

    }

    private void getData() {
        users = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference()
                .child(Constants.Users.key)
                .orderByChild(Constants.Users.name)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                               users.add(snapshot.getValue(Users.class));
                           }

                        Log.i("dcghgsd", "onDataChange: "+users.toString());

                        recyclerView.setAdapter(new ListAdapter(users,ListActivity.this));



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
