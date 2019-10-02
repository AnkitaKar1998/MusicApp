package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FirstActivity extends AppCompatActivity {

    Context context;
    DatabaseHelper databaseHelper;

    ScrollView svRegistrationForm;
    TextInputLayout tilName, tilEmail, tilContactNo;
    TextInputEditText tietName, tietEmail, tietContactNo;
    RadioGroup rgGender;
    Button btnInsertData, btnViewUsers, btnViewDatabase;

    String name, email, contactNo, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        context = FirstActivity.this;
        databaseHelper = new DatabaseHelper(context);

        svRegistrationForm = findViewById(R.id.sv_registration_form);
        tilName = findViewById(R.id.til_name);
        tilEmail = findViewById(R.id.til_email);
        tilContactNo = findViewById(R.id.til_contact_no);
        tietName = findViewById(R.id.tiet_name);
        tietEmail = findViewById(R.id.tiet_email);
        tietContactNo = findViewById(R.id.tiet_contact_no);
        rgGender = findViewById(R.id.rg_gender);
        btnInsertData = findViewById(R.id.btn_insert_data);
        btnViewUsers = findViewById(R.id.btn_view_users);
        btnViewDatabase = findViewById(R.id.btn_view_database);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_male) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }
            }
        });

        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()) {
                    name = tietName.getText().toString();
                    email = tietEmail.getText().toString();
                    contactNo = tietContactNo.getText().toString();

                    boolean insertResult = databaseHelper.insertUserDetails(email, name, contactNo, gender);    // inserting data
                    if(insertResult) {
                        Toast.makeText(context, "Data inserted succesfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Data not inserted", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(context, FirstActivity.class);    // redirecting to same activity after inserting data
                    startActivity(intent);
                }
            }
        });

        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivity.class);  // Moving to next activity for data retrieval
                startActivity(intent);
            }
        });

        btnViewDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbmanager = new Intent(context,AndroidDatabaseManager.class);
                startActivity(dbmanager);                                    // Moving to next activity for viewing contents of database
            }
        });
    }


    /* Method for validating the feilds */
    public boolean validateForm() {
        if(tietName.getText().toString().isEmpty()) {
            tilName.setError("Enter Name");
            focusOnView(tilName);
            return false;
        } else if(tietEmail.getText().toString().isEmpty()) {
            tilEmail.setError("Enter Email");
            focusOnView(tilEmail);
            return false;
        } else if(tietContactNo.getText().toString().isEmpty()) {
            tilContactNo.setError("Enter Contact no");
            focusOnView(tilContactNo);
            return false;
        } else if(rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(context, "Select Gender", Toast.LENGTH_SHORT).show();
            focusOnView(rgGender);
            return false;
        } else {
            return true;
        }
    }

    /* Method for moving to a particular view*/  /* This method is not mandatory for MCC assignment */
    public void focusOnView(final View view) {
        svRegistrationForm.post(new Runnable() {
            @Override
            public void run() {
                svRegistrationForm.smoothScrollTo(0, view.getTop()-50);
            }
        });
    }

}
