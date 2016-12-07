package com.example.mmizukami.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    String username = "";
    String password = "";
    DBHelper db;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText loginNameEditText = (EditText) findViewById(R.id.laName);
        final EditText loginPasswordEditText = (EditText) findViewById(R.id.laPassword);
        final Button loginButton = (Button) findViewById(R.id.laLoginButton);
        final TextView registerHere = (TextView) findViewById(R.id.laRegisterHere);
        db.getReadableDatabase();





        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = loginNameEditText.getText().toString();
                password = loginPasswordEditText.getText().toString();
                User loginUser = null;
                userList = db.getAllUsers();
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
    }
}
