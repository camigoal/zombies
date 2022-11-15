package com.example.juegozombies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EscenarioJuego extends AppCompatActivity {

    String UIDS, NOMBRES,ZOMBIES;
    TextView TvContador,TvNombre,Tvtiempo;
    ImageView IvZombie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escenario_juego);

        IvZombie = findViewById(R.id.IvZombie);
        TvContador = findViewById(R.id.TvContador);
        TvNombre = findViewById(R.id.TvNombre);
        Tvtiempo = findViewById(R.id.Tvtiempo);

        Bundle intent = getIntent().getExtras();
        UIDS = intent.getString("UID");
        NOMBRES = intent.getString("NOMBRE");
        ZOMBIES = intent.getString( "ZOMBIE");
        TvNombre.setText (NOMBRES);
        TvContador.setText (ZOMBIES);

    }
}