package com.example.juegozombies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Menu extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference JUGADORES;

    TextView Zombies,uid,correo,nombre,Menutxt;
    Button CerrarSesion,Jugarbtn,AcercaDeBtn,PuntuacionesBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        //UBICACIÓN FUENTE
        String ubicacion = "fuentes/ZOMBIE.TTF";
        Typeface Tf = Typeface.createFromAsset(Menu.this.getAssets(), ubicacion);

        Zombies = findViewById(R.id.Zombies);
        uid = findViewById(R.id.uid);
        correo = findViewById(R.id.correo);
        nombre = findViewById(R.id.nombre);
        Menutxt = findViewById(R.id. Menutxt);

        firebaseDatabase = FirebaseDatabase.getInstance();
        JUGADORES = firebaseDatabase.getReference( "Db jugadores");

        CerrarSesion = findViewById(R.id.CerrarSesion);
        Jugarbtn = findViewById(R.id.Jugarbtn);
        AcercaDeBtn = findViewById(R.id.AcercaDeBtn);
        PuntuacionesBtn = findViewById(R.id.PuntuacionesBtn);

        Zombies.setTypeface(Tf);
        uid.setTypeface(Tf);
        correo.setTypeface(Tf);
        nombre.setTypeface(Tf);
        Menutxt.setTypeface(Tf);
        CerrarSesion.setTypeface(Tf);
        Jugarbtn.setTypeface(Tf);
        AcercaDeBtn.setTypeface(Tf);
        PuntuacionesBtn.setTypeface(Tf);


        Jugarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Menu.this, "JUGAR", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent( Menu. this, EscenarioJuego.class);

                String Uids = uid.getText().toString();
                String Nombres = nombre.getText().toString();
                String Zombies2 = Zombies.getText().toString();
                intent.putExtra (  "UID", Uids);
                intent.putExtra( "NOMBRE", Nombres);
                intent.putExtra(  "ZOMBIE", Zombies2);
                startActivity (intent); Toast.makeText( Menu. this, "ENVIANDO PARÁMETROS", Toast.LENGTH_SHORT).show();
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
    // CUANDO SE LOGEA EL JUGADOR//
    private void UsuarioLogueado() {
        if (user != null) {
            Consulta();
            Toast.makeText(this, "Jugador en linea", Toast.LENGTH_SHORT).show();
        }
        else{
            startActivity(new Intent(Menu.this, MainActivity.class));
            finish();
        }
    }

    // CONSULTA LOS DATOS DE LA BASE DE DATOS//
    private void Consulta() {
        Query query = JUGADORES.orderByChild("Email").equalTo(user.getEmail());
    query.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                String zombiesString = "" + dataSnapshot1.child("Zombis").getValue();
                String uidString = "" + dataSnapshot1.child("Uid").getValue();
                String emailString = "" + dataSnapshot1.child("Email").getValue();
                String nombreString = "" + dataSnapshot1.child("Nombre").getValue();

                Zombies.setText (zombiesString);
                uid.setText (uidString);
                correo.setText (emailString);
                nombre.setText(nombreString);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    }

    // METODO AL CERRAR SESION//
    private void CerrarSesion() {
        auth.signOut();
        startActivity (new Intent( Menu. this, MainActivity.class));
        Toast.makeText(this,"Has cerrado la sesión", Toast.LENGTH_SHORT).show();
    }
}
