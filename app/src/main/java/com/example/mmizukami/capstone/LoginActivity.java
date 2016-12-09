package com.example.mmizukami.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

        private String username = "";
        private String password = "";
        private DBHelper db;
        private ArrayList<User> userList;
        private EditText loginNameEditText;
        private EditText loginPasswordEditText;
        private Button loginButton;
        private TextView registerHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHelper(this);
        loginNameEditText = (EditText) findViewById(R.id.laName);
        loginPasswordEditText = (EditText) findViewById(R.id.laPassword);
        loginButton = (Button) findViewById(R.id.laLoginButton);
        registerHere = (TextView) findViewById(R.id.laRegisterHere);
        db.getReadableDatabase();
        userList = db.getAllUsers();
        db.close();



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = loginNameEditText.getText().toString();
                password = loginPasswordEditText.getText().toString();
                User loginUser = null;

                for (User singleUser : userList) {
                    if (singleUser.getUserName() == username) {
                        if (singleUser.getPassword() == password)
                            loginUser = singleUser;
                    }
                }

                if (loginUser == null) {
                    Log.e("User not found error", "Username and password mismatch or Username is incorrect, try again");
                    loginPasswordEditText.setText("");
                } else {      // start Menu activity
                    Intent loginIntent = new Intent(LoginActivity.this, MenuActivity.class);
                    loginIntent.putExtra("User",loginUser);
                    startActivity(loginIntent);
                }


            }

        });


        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));

            }
        });
    }
}
