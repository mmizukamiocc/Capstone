package com.example.mmizukami.capstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditPetInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText epiName;
    private Spinner epiPetType;
    private ImageView epicPetImageSelect;
    private EditText epiPetDescription;
    private CheckBox epiAdoptedCheckBox;

    // TODO: figure how to get user intent with the right pet data to edit.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet_info);

        epiName = (EditText) findViewById(R.id.epiName);
        epiPetType = (Spinner) findViewById(R.id.epiPetType);
        epicPetImageSelect = (ImageView) findViewById(R.id.epiPetImageSelect);
        epiPetDescription = (EditText) findViewById(R.id.epiPetDescription);
        epiAdoptedCheckBox = (CheckBox) findViewById(R.id.epiAdoptedCheckBox);

        ArrayAdapter petAdapter = ArrayAdapter.createFromResource(this, R.array.pet_types_array, android.R.layout.simple_spinner_item);
        epiPetType.setAdapter(petAdapter);
        epiPetType.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
        Toast.makeText(this, "You Selected " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Pet Type not selected.",
                Toast.LENGTH_SHORT).show();
    }
}
