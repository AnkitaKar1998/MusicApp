package com.example.musicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserRecyclerViewHolder> {

    Context context;
    ArrayList<ModelForUsers> usersList;

    public UserRecyclerViewAdapter(Context context, ArrayList<ModelForUsers> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UserRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_users, parent, false);
        UserRecyclerViewHolder userRecyclerViewHolder = new UserRecyclerViewHolder(view);
        return  userRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerViewHolder userRecyclerViewHolder, int position) {
        userRecyclerViewHolder.tvId.setText("Id: " + usersList.get(position).getId());
        userRecyclerViewHolder.tvName.setText("Name: " + usersList.get(position).getName());
        userRecyclerViewHolder.tvEmail.setText("Email: " + usersList.get(position).getEmail());
        userRecyclerViewHolder.tvContactNo.setText("Contact No: " + usersList.get(position).getContactNo());
        userRecyclerViewHolder.tvGender.setText("Gender: " + usersList.get(position).getGender());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class UserRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tvId, tvName, tvEmail, tvContactNo, tvGender;

        public UserRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvContactNo = itemView.findViewById(R.id.tv_contact_no);
            tvGender = itemView.findViewById(R.id.tv_gender);

        }
    }

}
