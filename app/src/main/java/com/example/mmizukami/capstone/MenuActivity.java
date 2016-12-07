package com.example.mmizukami.capstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    User loginUser;

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



        }

    }

}
