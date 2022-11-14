package com.example.juegozombies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    Button btnlogin, btnregistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btnlogin = findViewById(R.id.btnlogin);
        btnregistro = findViewById(R.id.btnregistro);

        //NOS LLEVA A LA ACTIVIDAD LOGIN
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(Splash.this, "Has hecho clic en Iniciar sesión", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
            }
        });

        //NOS LLEVA A LA ACTIVIDAD REGISTRO
        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Splash.this, Registro.class);
                startActivity(intent);

            }
        });
    }
}