package com.example.juegozombies;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class EscenarioJuego extends AppCompatActivity {

    String UIDS, NOMBRES,ZOMBIES;
    TextView TvContador,TvNombre,Tvtiempo;
    ImageView IvZombie;

    TextView AnchoTv, AltoTv;
    int AnchoPantalla;
    int AltoPantalla;

    Random aleatorio;

    int contador = 0;

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

        AnchoTv = findViewById(R.id.AnchoTv);
        AltoTv = findViewById(R.id.AltoTv);

        TvNombre.setText (NOMBRES);
        TvContador.setText (ZOMBIES);

        Pantalla();
        CuentaAtras ();

        //AL HACER CLIC EN ZOMBI CONTADOR AUMENTA DE 1 EN 1
        IvZombie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador++;
                TvContador.setText(String.valueOf(contador));

                IvZombie.setImageResource(R.drawable.zombieaplastado);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        IvZombie.setImageResource(R.drawable.zombie2);
                        Movimiento();
                    }
                },500);

            }
        });
    }

    //PARA OBTENER TAMAÑO PANTALLA
    private void Pantalla(){
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        AnchoPantalla = point.x;
        AltoPantalla = point.y;

        String ANCHOS = String.valueOf(AnchoPantalla);
        String ALTOS = String.valueOf(AltoPantalla);

        AnchoTv.setText(ANCHOS);
        AltoTv.setText(ALTOS);

        aleatorio = new Random();
    }

    private void Movimiento(){
        int min = 0;
        /*MAX CORDENADA X*/
        int MaximoX =AnchoPantalla - IvZombie.getWidth();
        /*MAX CORDENADA Y*/
        int MaximoY = AltoPantalla - IvZombie.getHeight();

        int randomX =aleatorio.nextInt(((MaximoX - min) + 1) + min);
        int randomY = aleatorio.nextInt(((MaximoY - min) + 1) + min);

        IvZombie.setX(randomX);
        IvZombie.setY(randomY);
    }
    private void CuentaAtras () {
     new CountDownTimer( 30000, 1000) {
         public void onTick(long millisUntilFinished) {
             long segundosRestantes = millisUntilFinished / 1000;
             Tvtiempo.setText(segundosRestantes + "S");
         }
    public void onFinish() {
                 Tvtiempo.setText("0S");
             }
         }.start();
     }
}