package com.example.emailex.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.emailex.R;
import com.example.emailex.adapter.NewAdapter;
import com.example.emailex.firebase.Constants;
import com.example.emailex.model.Users;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NewAdapter newAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        adapter();

    }

    private void adapter() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newAdapter = new NewAdapter(new FirebaseRecyclerOptions.Builder<Users>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"), Users.class)
                .build(), this);
        recyclerView.setAdapter(newAdapter);
        newAdapter.startListening();

    }

    @Override
    protected void onPause() {
        super.onPause();
        newAdapter.stopListening();

    }
}
