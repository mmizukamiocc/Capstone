package com.example.mmizukami.capstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText userName = (EditText) findViewById(R.id.suUserName);
        final EditText password = (EditText) findViewById(R.id.suPassword);
        final EditText emailAddress = (EditText) findViewById(R.id.suEmailAddress);
        final EditText phone = (EditText) findViewById(R.id.suUserPhone);
        final EditText postalAddress = (EditText) findViewById(R.id.suPostalAddress);
        final Button register = (Button) findViewById(R.id.suRegister);

    }
}
