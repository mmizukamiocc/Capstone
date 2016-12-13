package com.example.mmizukami.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * EditUserInfo page use to edit user info.
 * It starts MenuActivity when edit has been done.
 *
 *  @input - EditText euiName, EditText euiEmailAddress, EditText euiPhone,
 *          EditText euiOldPassword, EditText euiNewPassword,
 *  @author Son Nguyen
 */
public class EditUserInfoActivity extends AppCompatActivity {

    private User loginUser;
    private String name;
    private String email;
    private String phone;
    private String oldPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);

        final EditText euiName = (EditText) findViewById(R.id.euiName);
        final EditText euiEmailAddress = (EditText) findViewById(R.id.euiEmailAddress);
        final EditText euiPhone = (EditText) findViewById(R.id.euiPhone);
        final EditText euiOldPassword = (EditText) findViewById(R.id.euiOldPassword);
        final EditText euiNewPassword = (EditText) findViewById(R.id.euiNewPassword);
        final Button euiUpdate = (Button) findViewById(R.id.euiUpdate);


        Intent updateIntent = getIntent();
        loginUser = updateIntent.getParcelableExtra("User");

        name = loginUser.getRealName();
        email = loginUser.getEmail();
        phone = loginUser.getPhone();
        oldPassword = loginUser.getPassword();

        euiName.setText(name);
        euiEmailAddress.setText(email);
        euiPhone.setText(phone);


        if (oldPassword.equals(euiOldPassword.getText().toString()))
        {
            if (euiName.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter a name.", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (euiEmailAddress.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter a email address.", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (euiPhone.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter a phone number.", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (euiNewPassword.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter a new password.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!euiNewPassword.getText().toString().trim().isEmpty())
                euiUpdate.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        loginUser.setRealName(euiName.getText().toString());
                        loginUser.setEmail(euiEmailAddress.getText().toString());
                        loginUser.setPhone(euiPhone.getText().toString());
                        loginUser.setPassword(euiNewPassword.getText().toString());

                        Intent addIntent = new Intent(EditUserInfoActivity.this, MenuActivity.class);
                        addIntent.putExtra("User", loginUser);
                        startActivity(addIntent);
                    }
                });
            }
        else
        {
            Toast.makeText(this, euiOldPassword.getText().toString() + " is Incorrect",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

