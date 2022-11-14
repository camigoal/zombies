package com.example.juegozombies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    TextView MiPuntuaciontxt,uid,correo,nombre,Menutxt;
    Button CerrarSesion,Jugarbtn,AcercaDeBtn,PuntuacionesBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        MiPuntuaciontxt = findViewById(R.id.MiPuntuaciontxt);
        uid = findViewById(R.id.uid);
        correo = findViewById(R.id.correo);
        nombre = findViewById(R.id.nombre);
        Menutxt = findViewById(R.id. Menutxt);



        CerrarSesion = findViewById(R.id.CerrarSesion);
        Jugarbtn = findViewById(R.id.Jugarbtn);
        AcercaDeBtn = findViewById(R.id.AcercaDeBtn);
        PuntuacionesBtn = findViewById(R.id.PuntuacionesBtn);

        Jugarbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(Menu.this, "JUGAR", Toast.LENGTH_SHORT).show();
            }
        });
        PuntuacionesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( Menu.this,  "PUNTUACIONES", Toast.LENGTH_SHORT).show();
            }
        });
        AcercaDeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( Menu. this,  "ACERCA DE", Toast.LENGTH_SHORT).show();
            }
        });


        CerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CerrarSesion();
            }
        });
    }
    // ESTE METODO SE EJECUTA CUANDO SE ABRE EL MINIJUEGO//
    @Override
    protected void onStart() {
        UsuarioLogueado();
        super.onStart();
    }
    private void UsuarioLogueado() {
        if (user != null) {
            Toast.makeText(this, "Jugador en linea", Toast.LENGTH_SHORT).show();
        }
        else{
            startActivity(new Intent(Menu.this, MainActivity.class));
            finish();
        }
    }
    private void CerrarSesion() {
        auth.signOut();
        startActivity (new Intent( Menu. this, MainActivity.class));
        Toast.makeText(this,"Cerrado sesión exitosamente", Toast.LENGTH_SHORT).show();
    }
}