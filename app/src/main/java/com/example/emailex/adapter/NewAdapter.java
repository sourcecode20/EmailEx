package com.example.emailex.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emailex.R;
import com.example.emailex.model.Users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NewAdapter extends FirebaseRecyclerAdapter<Users, NewAdapter.ListViewHolder> {

    Context context;


    public NewAdapter(@NonNull FirebaseRecyclerOptions<Users> options, Context context) {
        super(options);
        this.context = context;
        Log.i("ahdggcyjysd", "NewAdapter: ");
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user, parent, false);

        return new ListViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i, @NonNull Users users) {
        listViewHolder.name.setText(users.getName());
        listViewHolder.age.setText(users.getAge());
        listViewHolder.mobile.setText(users.getMobile());
        listViewHolder.address.setText(users.getAddress());
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, mobile, address;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.userName);
            age = itemView.findViewById(R.id.userAge);
            mobile = itemView.findViewById(R.id.userMobile);
            address = itemView.findViewById(R.id.userAddress);
        }

    }
}
