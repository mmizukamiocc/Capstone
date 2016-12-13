package com.example.mmizukami.capstone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
/**
 * Find pet page for the app
 * It find specific pet information from database.
 * It uses Pet and Relation table in the database to find all information,
 *  and show as a list founded by user input
 *
 *  @author Mahiro Mizukami
 */
public class FindPetsActivity extends AppCompatActivity {


    private DBHelper db;
    private List<Pet> allPets;
    private User user;
    private List<Pet> filteredPetList;
    private List<Relation> allRelations;

    private EditText findTypeEditText;
    private Spinner choiceSpinner;
    private ListView petsListView;

    private FindPetListAdapter findPetListAdapter;


    /**
     * onCreate method to get all data from database and set views to the member variables, and set adapter
     *
     *  @author Mahiro Mizukami
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pets);
        db = new DBHelper(this);
        Intent userIntent = getIntent();
        String[] choice = new String[] {"What you find?","Adoption","Lost","My Pet"};

        test();

        allPets = db.getAllPets();
        allRelations = db.getAllRelations();
        user =  userIntent.getParcelableExtra("User");
        choiceSpinner = (Spinner) findViewById(R.id.choiceSpinner);

        filteredPetList = new ArrayList<>(allPets);
        findPetListAdapter = new FindPetListAdapter(FindPetsActivity.this,R.layout.pet_list_item,filteredPetList,allRelations);
        findTypeEditText =(EditText) findViewById(R.id.findTypeEditText);
        findTypeEditText.addTextChangedListener(findTypeTextWatcher);
        petsListView = (ListView) findViewById(R.id.petsListView);

        ArrayAdapter<String> choiceSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,choice);
        choiceSpinner.setAdapter(choiceSpinnerAdapter);
        choiceSpinner.setOnItemSelectedListener(choiceSpinnerListener);
        petsListView.setAdapter(findPetListAdapter);



    }

    /**
     *  test cases for the petList, but it does not work well
     *  it have to have relations too
     *  @author Mahiro Mizukami
     */
    private void test()
    {


        Pet testPet1 = new Pet("Jhin","Dog","Strong.",false,false);
        Pet testPet2 = new Pet("Ahri","Cat","Cute.",true,false);
        Pet testPet3 = new Pet("Jax","Fish","Hop Step Jump",false,true);
        Pet testPet4 = new Pet("Ashe","Bird","Fly as a wind",false,false);
        filteredPetList.add(testPet1);
       filteredPetList.add(testPet2);
        filteredPetList.add(testPet3);
        filteredPetList.add(testPet4);
    }

    /**
     * onClick event for the ListView, it send selected pet and login user as intent to PetDetailActivity
     *  @param view selected item from ListView
     *  @author Mahiro Mizukami
     */
    public void viewPetDetail(View view)
    {
        Intent detailIntent = new Intent(FindPetsActivity.this,PetDetailsActivity.class);
        Pet pet = (Pet) view.getTag();
        detailIntent.putExtra("Pet",pet);
        detailIntent.putExtra("User",user);
        startActivity(detailIntent);
    }

    public TextWatcher findTypeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String input = charSequence.toString().toLowerCase();

            if(!input.equals(""))
            {
                for(Pet pet: allPets)
                {
                    if(!pet.getType().toLowerCase().contains(input))
                        findPetListAdapter.remove(pet);

                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    public AdapterView.OnItemSelectedListener choiceSpinnerListener  = new AdapterView.OnItemSelectedListener()
    {


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            switch(i)
            {
                case 0:
                    filteredPetList.clear();
                    for(Pet pet : allPets) {
                        filteredPetList.add(pet);
                        findPetListAdapter.add(pet);
                    }
                    break;

                case 1:

                    for(Pet singlePet : filteredPetList)
                    {
                        if(!singlePet.isAdopted()) {
                            filteredPetList.remove(singlePet);
                            findPetListAdapter.remove(singlePet);
                        }
                    }

                    break;

                case 2:
                    for(Pet singlePet : filteredPetList)
                    {
                        if(!singlePet.isLost())
                        {
                            filteredPetList.remove(singlePet);
                            findPetListAdapter.remove(singlePet);
                        }
                    }

                    break;

                case 3:
                for(Relation singleRelation: allRelations) {
                  if(!(singleRelation.getUser() == user))
                  {
                      filteredPetList.remove(singleRelation.getPet());
                      findPetListAdapter.remove(singleRelation.getPet());
                  }
                }

                    break;
            }

        }



        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}
