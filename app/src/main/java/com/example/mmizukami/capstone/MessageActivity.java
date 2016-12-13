package com.example.mmizukami.capstone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Message page use to send message to other users.
 * It starts MenuActivity when edit has been done.
 *
 *  @input - String epiName, Spinner epiPetType, Uri imageUrl,
 *          EditText epiPetDescription, CheckBox epiAdoptedCheckBox,
 *  @author Son Nguyen
 */
public class MessageActivity extends AppCompatActivity {

    //private static final int REQUEST_CODE_ADD_CONTACT = 200;
    private static final int REQUEST_CODE_SEND_SMS = 201;


    private User loginUser;
    private ArrayList<User> userContact;
    private ContactsAdapter contactsAdapter;
    private DBHelper db;
    private ListView contactsListView;
    private EditText messageEditText;
    private Button sendTextMessageButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent userIntent = getIntent();
        loginUser = userIntent.getParcelableExtra("User");

        db = new DBHelper(this);
        userContact = db.getAllUsers();
        contactsAdapter = new ContactsAdapter(this, R.layout.contact_list_item, userContact);
        contactsListView = (ListView) findViewById(R.id.contactsListView);
        contactsListView.setAdapter(contactsAdapter);

        messageEditText = (EditText) findViewById(R.id.messageEditText);
        sendTextMessageButton = (Button) findViewById(R.id.sendTextMessageButton);

        contactsAdapter.add(loginUser);


    }

    public void sendTextMessage(View view) {
        String message = messageEditText.getText().toString();
        if (message.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a message.", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (userContact.size() == 0) {
            Toast.makeText(this, "There is no contact.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Checking for permission to send text message:
        else if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.SEND_SMS}, REQUEST_CODE_SEND_SMS);
        }
        else {
            // Define a reference to SmsManager (manages text messages)
            SmsManager manager = SmsManager.getDefault();
            for (User singleContact : userContact)
                manager.sendTextMessage(singleContact.getPhone(), null, message, null, null);

            messageEditText.setText("");
        }
    }
}
