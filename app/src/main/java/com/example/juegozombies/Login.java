package com.example.juegozombies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText correoLogin, passLogin;
    Button btnLogin;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //CONEXIÓN CON LA VISTA
        correoLogin = findViewById(R.id.correoLogin);
        passLogin = findViewById(R.id.passLogin);
        btnLogin = findViewById(R.id.btnLogin);
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = correoLogin.getText().toString();
                String pass = passLogin.getText().toString();

                /*VALIDACIÓN CORREO ELECTRÓNICO*/
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    correoLogin.setError("Correo inválido");
                    correoLogin.setFocusable(true);
                    /*VALIDACIÓN CONTRASEÑA*/
                }else if(pass.length()<6) {
                    passLogin.setError("Utiliza seis caracteres como mínimo con una combinación de letras, números y símbolos");
                    passLogin.setFocusable(true);
                }else {
                    LogueoJugador(email, pass);

                }

            }
        });
    }

//MÉTODO PARA LOGUEAR AL JUGADOR
    private void LogueoJugador(String email, String pass) {
            auth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = auth.getCurrentUser();
                                startActivity(new Intent(Login.this, Menu.class));
                                Toast.makeText(Login.this, "Bienvenido (a)" + user.getEmail(), Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this,"" +e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

    }
}