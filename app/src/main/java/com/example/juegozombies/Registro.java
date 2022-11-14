package com.example.juegozombies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Registro extends AppCompatActivity {

    //DECLARACIÓN DE VARIABLES
    EditText correo, pass, nombre;
    TextView fechaText;
    Button registrarte;
    FirebaseAuth auth; //FIREBASE AUTENTICACIÓN

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //CONEXIÓN CON LA VISTA
        correo = findViewById(R.id.correo);
        pass = findViewById(R.id.pass);
        nombre = findViewById(R.id.nombre);
        fechaText = findViewById(R.id.fecha);
        registrarte = findViewById(R.id.registrarte);

        auth = FirebaseAuth.getInstance();

        Date date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("d 'de' MMMM 'de' yyyy" ); /*16 de noviembre de 2022*/
        String StringFecha = fecha.format(date);
        fechaText.setText(StringFecha);

        registrarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String email = correo.getText().toString();
               String password = pass.getText().toString();

                    /*VALIDACIÓN CORREO ELECTRÓNICO*/
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    correo.setError("Correo inválido");
                    correo.setFocusable(true);
                    /*VALIDACIÓN CONTRASEÑA*/
                }else if(password.length()<6) {
                    pass.setError("Utiliza seis caracteres como mínimo con una combinación de letras, números y símbolos");
                    correo.setFocusable(true);
                }else {
                    RegistrarJugador(email, password);

                }
            }
        });
    }

    //MÉTODO PARA REGISTRAR JUGADORES
    private void RegistrarJugador(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        /*SI EL JUGADOR FUE REGISTRADO EXITOSAMENTE*/
                        if(task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();

                            int contador = 0;

                            assert user != null; //USUARIO NO NULO

                            String uidString = user.getUid();
                            String correoString = correo.getText().toString();
                            String passString = pass.getText().toString();
                            String nombreString = nombre.getText().toString();
                            String fechaString = fechaText.getText().toString();

                            HashMap<Object,Object> DatosJUGADOR = new HashMap<>();

                            DatosJUGADOR.put("Uid", uidString);
                            DatosJUGADOR.put("Email", correoString);
                            DatosJUGADOR.put("Password", passString);
                            DatosJUGADOR.put("Nombre", nombreString);
                            DatosJUGADOR.put("Fecha", fechaString);
                            DatosJUGADOR.put("Zombis", contador);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();/*INSTANCIA*/
                            DatabaseReference reference = database.getReference("Db jugadores");/*NOMBRE DB*/
                            reference.child(uidString).setValue(DatosJUGADOR);
                            startActivity(new Intent(Registro.this,Menu.class));
                            Toast.makeText(Registro.this, "Su usuario ha sido registrado exitosamente", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(Registro.this, "Ha ocurrido un error inesperado", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                /*SI FALLA EL REGISTRO*/
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registro.this, ""+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
    }
}