package com.example.juegozombies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimerTask tiempo = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Splash.class);
                startActivity(intent);
                finish();
            }
        };

        Timer valor = new Timer();
        valor.schedule(tiempo,3000);

    }
}