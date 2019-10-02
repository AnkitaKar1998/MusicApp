package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Context context;
    DatabaseHelper databaseHelper;

    ArrayList<ModelForUsers> userList = new ArrayList();
    RecyclerView rvUserDetails;
    RecyclerView.Adapter adapter;
    TextView tvMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        context = SecondActivity.this;
        databaseHelper = new DatabaseHelper(context);

        rvUserDetails = findViewById(R.id.rv_user_details);
        tvMessage = findViewById(R.id.tv_message);

        Cursor cursor = databaseHelper.getAllUserDetails();          // Retriving data from database
        if(cursor.getCount() == 0) {
            tvMessage.setVisibility(View.VISIBLE);
        } else {
            tvMessage.setVisibility(View.GONE);
            while (cursor.moveToNext()) {
                ModelForUsers user = new ModelForUsers();
                user.setId(cursor.getString(0));
                user.setName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setContactNo(cursor.getString(3));
                user.setGender(cursor.getString(4));
                userList.add(user);
            }

            rvUserDetails.setHasFixedSize(true);
            rvUserDetails.setLayoutManager(new LinearLayoutManager(this));
            adapter = new UserRecyclerViewAdapter(context, userList);
            rvUserDetails.setAdapter(adapter);
        }

    }
}
