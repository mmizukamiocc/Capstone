package com.example.mmizukami.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    User userArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText uaUserName = (EditText) findViewById(R.id.uaUserName);
        final EditText uaName = (EditText) findViewById(R.id.uaName);
        final EditText uaEmailAddress = (EditText) findViewById(R.id.uaEmailAddress);
        final EditText uaPhone = (EditText) findViewById(R.id.uaPhone);
        final Button uaEdit = (Button) findViewById(R.id.uaEdit);
        final TextView uaWelcomeMessage = (TextView) findViewById(R.id.uaWelcomeMessage);

        Intent userAreaIntent = getIntent();
        userArea = userAreaIntent.getParcelableExtra("User");

        String username = userArea.getUserName();
        String name = userArea.getRealName();
        String email = userArea.getEmail();
        String phone = userArea.getPhone();


        //String username = userAreaIntent.getStringExtra("username");
        //String name = userAreaIntent.getStringExtra("real_name");
        //String email = userAreaIntent.getStringExtra("email");
        //String phone = userAreaIntent.getStringExtra("phone");

        String message = name + " welcome to your user area.";
        uaWelcomeMessage.setText(message);
        uaUserName.setText(username);
                uaName.setText(name);
                uaEmailAddress.setText(email);
                uaPhone.setText(phone);

                uaEdit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent editIntent = new Intent(UserAreaActivity.this, EditUserInfoActivity.class);
                        editIntent.putExtra("User", userArea);
                        startActivity(editIntent);
            }
        });
    }
}
