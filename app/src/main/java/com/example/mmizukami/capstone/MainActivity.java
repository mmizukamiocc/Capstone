package com.example.mmizukami.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button logInButton;
    private Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logInButton = (Button) findViewById(R.id.logInButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);

    }


    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.logInButton:
                // start login activity
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            break;


            case R.id.signUpButton:
                // start sign up activity
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            break;
        }

    }

}
