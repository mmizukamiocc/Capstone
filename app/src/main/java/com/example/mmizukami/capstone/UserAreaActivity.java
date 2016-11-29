package com.example.mmizukami.capstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        EditText uaUserName = (EditText) findViewById(R.id.uaName);
        EditText uaEmailAddress = (EditText) findViewById(R.id.uaEmailAddress);
        EditText uaPhone = (EditText) findViewById(R.id.uaPhone);
        EditText uaAddress = (EditText) findViewById(R.id.uaAddress);

    }
}
