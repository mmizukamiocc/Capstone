package com.example.mmizukami.capstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText UaaUserName = (EditText) findViewById(R.id.userName);
        final EditText uaaPassword = (EditText) findViewById(R.id.password);
        final EditText uaaEmailAddress = (EditText) findViewById(R.id.email);
        final EditText uaaPhone = (EditText) findViewById(R.id.phone);
        final EditText uaaAddress = (EditText) findViewById(R.id.address);
        final Button uaaRegister = (Button) findViewById(R.id.register);
    }
}
