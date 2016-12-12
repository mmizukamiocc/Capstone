package com.example.mmizukami.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Menu page for the app
 * It starts other activity with login information
 *
 *  FindPetsActivity - find pet for adoption,lost pet,or my pet information
 *  AddPetActivity - add Pet object to the database
 *  UserAreaActivity - check and edit user information
 *  MapActivity - find places which help pets
 *
 *  @author Mahiro Mizukami
 */
public class MenuActivity extends AppCompatActivity {

    private User loginUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent userIntent = getIntent();
        loginUser = userIntent.getParcelableExtra("User");
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.findButton:
                // start login activity
                Intent findIntent =new Intent(MenuActivity.this,FindPetsActivity.class);
                findIntent.putExtra("User",loginUser);
                startActivity(findIntent);
                break;

            case R.id.addButton:
                // start user area activity
                Intent addIntent = new Intent(MenuActivity.this, AddPetActivity.class);
                addIntent.putExtra("User", loginUser);
                startActivity(addIntent);

                break;

            case R.id.userButton:
                // start user area activity
                Intent userIntent = new Intent(MenuActivity.this, UserAreaActivity.class);
                userIntent.putExtra("User", loginUser);
                startActivity(userIntent);
                break;

           case R.id.mapButton:
                // start map activity
               Intent mapIntent = new Intent(MenuActivity.this, MapActivity.class);
               mapIntent.putExtra("User", loginUser);
               startActivity(mapIntent);
               break;


        }

    }

}
