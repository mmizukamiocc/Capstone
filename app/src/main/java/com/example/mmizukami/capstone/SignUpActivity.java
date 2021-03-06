package com.example.mmizukami.capstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * Sign up page for the app
 * It add a user information to the database and start LoginActivity
 *
 *  @input - String username,String realName, String email, String phone, String password
 *  @author Mahiro Mizukami
 */
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
        db = new DBHelper(this);
        userNameEditText = (EditText) findViewById(R.id.suUserName);
        realNameEditText = (EditText) findViewById(R.id.suRealName);
        passwordEditText = (EditText) findViewById(R.id.suPassword);
        emailAddressEditText = (EditText) findViewById(R.id.suEmailAddress);
        phoneEditText = (EditText) findViewById(R.id.suUserPhone);

        registerButton = (Button) findViewById(R.id.suRegister);

        allUserList = db.getAllUsers();


    }

    /**
     * onClick event to sign up button
     * get all data from user input, and if there is no username duplication and password is 8digits, send it to database
     *Sign
     *  @param view sign up button
     *  @input String username, String password, String realName, String emailAddress, String phone
     *  @author Mahiro Mizukami
     */
    public void onClick(View view) {
        boolean valid = true;

        String name = "";
        String pass = "";

        if(userNameEditText.getText().length() !=0)
            name = userNameEditText.getText().toString();
        else
        {
            Toast.makeText(this, "Username field must be fill. Please try again.", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        if(passwordEditText.getText().length() !=0)
            pass = passwordEditText.getText().toString();
        else
        {
            Toast.makeText(this, "Password field must be fill. Please try again.", Toast.LENGTH_SHORT).show();

            valid = false;
        }

            String realName = realNameEditText.getText().toString();
            String email = emailAddressEditText.getText().toString();
            String phone = phoneEditText.getText().toString();


        if(pass.length() != 8)
        {
            Toast.makeText(this, "Password must be 8 character. Please try again.", Toast.LENGTH_SHORT).show();
            passwordEditText.setText("");
            valid = false;
        }

        for(User singleUser : allUserList)
        {
            if(singleUser.getUserName().equals(name))
            {
                Toast.makeText(this, "Username duplicated. Please use other name.", Toast.LENGTH_SHORT).show();
                userNameEditText.setText("");
                valid = false;
            }

        }

        if(valid)
            newUser = new User(name, realName, email, phone, pass);
            db.addUser(newUser);
            Toast.makeText(this, "Sign in completed.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        }

    }


