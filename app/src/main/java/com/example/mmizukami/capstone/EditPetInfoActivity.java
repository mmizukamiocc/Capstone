package com.example.mmizukami.capstone;



import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditPetInfoActivity extends AppCompatActivity {

    User loginUser;
    Relation petUpdate;

    private EditText epiName;
    private Spinner epiPetType;
    private ImageView epiPetImageSelect;
    private EditText epiPetDescription;
    private CheckBox epiAdoptedCheckBox;
    private static final int REQUEST_CODE = 200;
    private Uri imageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet_info);

        Intent userIntent = getIntent();
        loginUser = userIntent.getParcelableExtra("User");

        epiName = (EditText) findViewById(R.id.epiName);
        epiPetType = (Spinner) findViewById(R.id.epiPetType);
        epiPetImageSelect = (ImageView) findViewById(R.id.epiPetImageSelect);
        epiPetDescription = (EditText) findViewById(R.id.epiPetDescription);
        epiAdoptedCheckBox = (CheckBox) findViewById(R.id.epiAdoptedCheckBox);


        ArrayAdapter<String> petTypeSpinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.pet_types_array));
        epiPetType.setAdapter(petTypeSpinnerAdapter);

        imageURI = getUriToResource(this, R.drawable.dog);
        epiPetImageSelect.setImageURI(imageURI);
    }

    public void selectPetImage (View view) {

        ArrayList<String> permissionsList = new ArrayList<>();

        int cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        if (cameraPermission != PackageManager.PERMISSION_GRANTED)
            permissionsList.add(android.Manifest.permission.CAMERA);

        int readExStorage = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readExStorage != PackageManager.PERMISSION_GRANTED)
            permissionsList.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);

        int writeExStorage = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writeExStorage != PackageManager.PERMISSION_GRANTED)
            permissionsList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

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
            epiPetImageSelect.setImageURI(imageURI);
        }
    }

    public static Uri getUriToResource(@NonNull Context context, @AnyRes int resId) throws Resources.NotFoundException {
        Resources res = context.getResources();

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId));
    }

    public void onUpdate(View view) {

        if (epiName.getText().toString().equals("") ||
                epiPetDescription.getText().toString().equals("")) {

            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_SHORT).show();
        }
        else if (imageURI == getUriToResource(this, R.drawable.dog))
        {

        }
        else {
            Pet petToAdd = new Pet (epiName.getText().toString(),
                    epiPetType.getSelectedItem().toString(),
                    epiPetDescription.getText().toString(),
                    epiAdoptedCheckBox.isChecked(),
                    false,
                    imageURI);

            if (loginUser.getId() == petUpdate.getUser().getId())
            {
                petUpdate.setPet(petToAdd);
            }

            Intent addIntent = new Intent(EditPetInfoActivity.this, MenuActivity.class);
            addIntent.putExtra("User", loginUser);
            startActivity(addIntent);
        }

    }
}
