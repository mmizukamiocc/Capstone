package com.example.mmizukami.capstone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Start page for the app
 * It starts other activity login or sign up
 *
 *  LoginActivity - enter username and password to login
 *  SignUpActivity - add user information to the database
 *  @author Mahiro Mizukami
 */
public class MainActivity extends AppCompatActivity {

    private Button logInButton;
    private Button signUpButton;
    private static MainActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logInButton = (Button) findViewById(R.id.logInButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        mContext = this;
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


    /**
     * Send the Context of this class to other classes, I'm not sure it work or not
     *  @return MainActivity to get Context;
     *  @author Mahiro Mizukami
     */
    public static synchronized MainActivity getInstance()
    {
        return mContext;
    }
}
