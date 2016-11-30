package com.example.mmizukami.capstone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by Ethan on 11/22/2016.
 */
//public class AddPetActivity extends AppCompatActivity {

    private EditText petNameEntryEditText;
    private EditText petTypeEditText;
    private EditText petDescriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        petNameEntryEditText = (EditText) findViewById(R.id.petNameEntryEditText);
        petTypeEditText = (EditText) findViewById(R.id.petTypeEditText);

        /*
        I intend to change petTypeEditText to a Spinner with some preset categories.
        Some of these categories include dog, cat, fish, rodent, lizard, and other.
        */

        petDescriptionEditText = (EditText) findViewById(R.id.petDescriptionEditText);

    }

    /*
    public void onNextClick (View view) {

        // TODO: Add case for when fields are not filled out.

        if (view.getId() == R.id.addPetNextButton) {
            String name = petNameEntryEditText.getText().toString();
            String type = petTypeEditText.getText().toString();
            String desc = petDescriptionEditText.getText().toString();
            Intent addPetContinueIntent = new Intent(AddPetActivity.this, AddPet2Activity.class);

            // TODO: Actually make AddPet2Activity.class .

            addPetContinueIntent.putExtra("Pet Name", name);
            addPetContinueIntent.putExtra("Pet Type", type);
            addPetContinueIntent.putExtra("Pet Desc", desc);
            startActivity(addPetContinueIntent);
        }
    }
    */
}
