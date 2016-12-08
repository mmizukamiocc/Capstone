package com.example.mmizukami.capstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {


    private EditText passwordEditText;
    private EditText userNameEditText;
    private EditText realNameEditText;
    private EditText emailAddressEditText;
    private EditText phoneEditText;
    private Button registerButton;
    private DBHelper db;
    private User newUser;
    private ArrayList<User> allUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userNameEditText = (EditText) findViewById(R.id.suUserName);
        realNameEditText = (EditText) findViewById(R.id.suRealName);
        passwordEditText = (EditText) findViewById(R.id.suPassword);
        emailAddressEditText = (EditText) findViewById(R.id.suEmailAddress);
        phoneEditText = (EditText) findViewById(R.id.suUserPhone);

        registerButton = (Button) findViewById(R.id.suRegister);

        db.getReadableDatabase();
        allUserList = db.getAllUsers();

    }

    public void onClick(View view) {
        boolean valid = true;

        String name = "";
        String pass = "";

        if(userNameEditText.getText().length() !=0)
            name = userNameEditText.getText().toString();
        else
        {
            //toast short message and do not do anything
            valid = false;
        }

        if(passwordEditText.getText().length() !=0)
            pass = passwordEditText.getText().toString();
        else
        {
            //toast short message and do not do anything
            valid = false;
        }

            String realName = realNameEditText.getText().toString();
            String email = emailAddressEditText.getText().toString();


            String phone = phoneEditText.getText().toString();


        if(pass.length() != 8)
        {
            //toast short message and do not do anything
            valid = false;
        }

        for(User singleUser : allUserList)
        {
            if(singleUser.getUserName() == name)
            {
                //toast short message and do not do anything
                valid = false;
            }

        }

        if(valid) {
            newUser = new User(name, realName, email, phone, pass);
            db.addUser(newUser);
            // toast success message and send to login activity

            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        }

    }

}
