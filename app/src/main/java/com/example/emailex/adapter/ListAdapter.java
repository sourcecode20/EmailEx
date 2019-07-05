package com.example.emailex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emailex.R;
import com.example.emailex.model.Users;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private List<Users> users;
    private Context context;

    public ListAdapter(@NonNull List<Users> users, Context context) {

        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        listViewHolder.name.setText(users.get(i).getName());
        listViewHolder.age.setText(users.get(i).getAge());
        listViewHolder.mobile.setText(users.get(i).getMobile());
        listViewHolder.address.setText(users.get(i).getAddress());

    }

    @Override
    public int getItemCount() {
        return users.size();
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
