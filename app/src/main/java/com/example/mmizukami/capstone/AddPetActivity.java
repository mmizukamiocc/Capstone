package com.example.mmizukami.capstone;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ethan on 11/22/2016.
 */
public class AddPetActivity extends AppCompatActivity {

    private EditText petNameEntryEditText;
    private Spinner petTypeSpinner;
    private EditText petDescriptionEditText;
    private ImageView petImageSelectImageView;
    private CheckBox isAdoptedCheckBox;
    private User loginUser;
    private DBHelper db;
    private static final int REQUEST_CODE = 573;
    private Uri imageURI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
        db = new DBHelper(this);
        Intent userIntent = getIntent();
        loginUser = userIntent.getParcelableExtra("User");


        petNameEntryEditText = (EditText) findViewById(R.id.petNameEntryEditText);
        petTypeSpinner = (Spinner) findViewById(R.id.petTypeSpinner);
        petDescriptionEditText = (EditText) findViewById(R.id.petDescriptionEditText);
        petImageSelectImageView = (ImageView) findViewById(R.id.petImageSelectImageView);
        isAdoptedCheckBox = (CheckBox) findViewById(R.id.isAdoptedCheckBox);

        // Setup for petTypeSpinner
        ArrayAdapter<String> petTypeSpinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.pet_types_array));
        petTypeSpinner.setAdapter(petTypeSpinnerAdapter);

        // Setup for petImageSelectImageView
        imageURI = getUriToResource(this, R.drawable.dog);
        petImageSelectImageView.setImageURI(imageURI);

    }


    public void selectPetImage (View view) {

        ArrayList<String> permissionsList = new ArrayList<>();

        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (cameraPermission != PackageManager.PERMISSION_GRANTED)
            permissionsList.add(Manifest.permission.CAMERA);

        int readExStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readExStorage != PackageManager.PERMISSION_GRANTED)
            permissionsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        int writeExStorage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writeExStorage != PackageManager.PERMISSION_GRANTED)
            permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionsList.size() > 0) {
            String[] args = new String[permissionsList.size()];
            ActivityCompat.requestPermissions(this, permissionsList.toArray(args), REQUEST_CODE);
        }

        if (cameraPermission == PackageManager.PERMISSION_GRANTED &&
                readExStorage == PackageManager.PERMISSION_GRANTED &&
                writeExStorage == PackageManager.PERMISSION_GRANTED) {

            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, REQUEST_CODE);
        }
        else
            Toast.makeText(this, "Pet Protector requires camera and external storage permissions!", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            imageURI = data.getData();
            petImageSelectImageView.setImageURI(imageURI);
        }
    }

    public static Uri getUriToResource(@NonNull Context context, @AnyRes int resId) throws Resources.NotFoundException {
        Resources res = context.getResources();

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId));
    }

    public void onNextClick(View view) {

        if (petNameEntryEditText.getText().toString().equals("") ||
                petDescriptionEditText.getText().toString().equals("")) {

            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_SHORT).show();
        }
        else if (imageURI == getUriToResource(this, R.drawable.dog))
        {
            Toast.makeText(this, "Please select an image.",Toast.LENGTH_SHORT).show();
        }
        else {
             Pet petToAdd = new Pet (petNameEntryEditText.getText().toString(),
                    petTypeSpinner.getSelectedItem().toString(),
                    petDescriptionEditText.getText().toString(),
                    isAdoptedCheckBox.isChecked(),
                    false,
                    imageURI);
            Relation relationToAdd = new Relation(petToAdd,loginUser);

            db.addPet(petToAdd);
            db.addRelation(relationToAdd);
            Toast.makeText(this, petToAdd.getPetName() + " has been added to the list of pets.", Toast.LENGTH_SHORT).show();
            
            Intent addIntent = new Intent(AddPetActivity.this, MenuActivity.class);
            addIntent.putExtra("User", loginUser);
            startActivity(addIntent);
        }

    }

}
