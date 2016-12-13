package com.example.mmizukami.capstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Splash page use to show initial startup of app.
 * It starts Main to login.
 *
 *  @author Son Nguyen
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask mediaTask = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        };

        Timer timer = new Timer();
        timer.schedule(mediaTask, 5000);
    }
}
