package com.example.mmizukami.capstone;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ethan on 11/22/2016.
 */
public class PetDetailsActivity extends AppCompatActivity {

    private TextView petDetailNameTextView;
    private TextView petDetailDescriptionTextView;
    private ImageView petDetailImageView;
    private TextView petDetailStatusTextView;
    private User detailsUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        petDetailNameTextView = (TextView) findViewById(R.id.petDetailNameTextView);
        petDetailDescriptionTextView = (TextView) findViewById(R.id.petDetailDescriptionTextView);
        petDetailImageView = (ImageView) findViewById(R.id.petDetailImageView);
        petDetailStatusTextView = (TextView) findViewById(R.id.petDetailStatusTextView);

        Intent detailsIntent = getIntent();

        detailsUser = detailsIntent.getParcelableExtra("User");
        Pet detailsPet = detailsIntent.getParcelableExtra("Pet");
        String petImageUri = String.valueOf(detailsPet.getImageUri());

        petDetailNameTextView.setText(detailsPet.getPetName());
        petDetailDescriptionTextView.setText(detailsPet.getDescription());

        if (detailsPet.isAdopted())
            petDetailStatusTextView.setText(R.string.status_adopted);
        else if (detailsPet.isLost())
            petDetailStatusTextView.setText(R.string.status_lost);
        else
            petDetailStatusTextView.setText(R.string.status_adoptable);

        AssetManager am = this.getAssets();
        try {

            InputStream stream = am.open(detailsIntent.getStringExtra("ImageName"));
            Drawable event = Drawable.createFromStream(stream, petImageUri);
            petDetailImageView.setImageDrawable(event);
        }
        catch (IOException ex)
        {
            Log.e("Pet Protector", "Error loading " + petImageUri, ex);
        }


    }

    // TODO: Configure button that appears only if pet is lost or up for adoption
    // TODO: to send to user that owns the pet. Possibly send an Intent?
    public void onSMSClick (View view)
    {
        Intent SMSIntent = new Intent (PetDetailsActivity.this, MessageActivity.class);
        SMSIntent.putExtra("User", detailsUser);
        startActivity(SMSIntent);
    }
}
