package com.example.juegozombies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    //MÉTODO QUE SE EJECUTA CUANDO SE ABRE EL JUEGO
    @Override
    protected void onStart() {
        UsuarioLogueado();
        super.onStart();
    }

    //MÉTODO QUE COMPRUEBA SI EL JUGADOR HA INICIADO SESIÓN
    private void UsuarioLogueado() {
        if (user !=null) {
            Toast.makeText(this,"Jugador en línea", Toast.LENGTH_SHORT).show();
        }
        else {
            startActivity(new Intent(Menu.this,Splash.class));
            finish();
        }

    }
}