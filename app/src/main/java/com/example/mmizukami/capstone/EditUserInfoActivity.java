package com.example.mmizukami.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditUserInfoActivity extends AppCompatActivity {

    private User loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);

        final EditText euiName = (EditText) findViewById(R.id.euiName);
        final EditText euiEmailAddress = (EditText) findViewById(R.id.euiEmailAddress);
        final EditText euiPhone = (EditText) findViewById(R.id.euiPhone);
        final EditText euiOldPassword = (EditText) findViewById(R.id.euiOldPassword);
        final EditText euiPassword = (EditText) findViewById(R.id.euiPassword);
        final Button euiUpdate = (Button) findViewById(R.id.euiUpdate);


        Intent updateIntent = getIntent();
        loginUser = updateIntent.getParcelableExtra("User");

        String name = updateIntent.getStringExtra("real_name");
        String email = updateIntent.getStringExtra("email");
        String phone = updateIntent.getStringExtra("phone");
        String password = updateIntent.getStringExtra("password");

        euiName.setText(name);
        euiEmailAddress.setText(email);
        euiPhone.setText(phone);

        if (password == euiOldPassword.getText().toString())
        {
            if (euiName.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter a name.", Toast.LENGTH_SHORT).show();
                return;
            } else if (euiEmailAddress.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter a email address.", Toast.LENGTH_SHORT).show();
                return;
            } else if (euiPhone.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter a phone number.", Toast.LENGTH_SHORT).show();
                return;
            } else if (!euiName.getText().toString().trim().isEmpty() &&
                    !euiEmailAddress.getText().toString().trim().isEmpty() &&
                    !euiPhone.getText().toString().trim().isEmpty()) {
                euiUpdate.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        loginUser.setRealName(euiName.getText().toString());
                        loginUser.setEmail(euiEmailAddress.getText().toString());
                        loginUser.setPhone(euiPhone.getText().toString());
                        loginUser.setPassword(euiPassword.getText().toString());

                        Intent addIntent = new Intent(EditUserInfoActivity.this, MenuActivity.class);
                        addIntent.putExtra("User", loginUser);
                        startActivity(addIntent);
                    }
                });
            }
        }
        else
        {
            Toast.makeText(this, euiOldPassword + " is Incorrect",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

