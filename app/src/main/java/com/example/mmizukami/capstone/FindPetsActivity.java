package com.example.mmizukami.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FindPetsActivity extends AppCompatActivity {


    private DBHelper db;
    private List<Pet> allPets;
    private User user;
    private List<Pet> filteredPetList;

    private EditText findTypeEditText;
    private Spinner choiceSpinner;
    private ListView petsListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pets);

        Intent userIntent = getIntent();
        db.getReadableDatabase();
        String[] choice = new String[] {"What you find?","Adoption","Lost","My Pet"};
        allPets = db.getAllPets();
        user =  userIntent.getParcelableExtra("User");

        filteredPetList = new ArrayList<>(allPets);

       // findTypeEditText =(EditText) findViewById(R.id.findTypeEditText);

        ArrayAdapter<String> choiceSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,choice);
        choiceSpinner.setAdapter(choiceSpinnerAdapter);
        choiceSpinner.setOnItemSelectedListener(choiceSpinnerListener);



    }

    public AdapterView.OnItemSelectedListener choiceSpinnerListener  = new AdapterView.OnItemSelectedListener()
    {


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}
