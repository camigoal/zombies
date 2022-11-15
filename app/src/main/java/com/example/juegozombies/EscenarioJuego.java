package com.example.juegozombies;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class EscenarioJuego extends AppCompatActivity {

    String UIDS, NOMBRES,ZOMBIES;
    TextView TvContador,TvNombre,Tvtiempo;
    ImageView IvZombie;

    TextView AnchoTv, AltoTv;
    int AnchoPantalla;
    int AltoPantalla;

    Random aleatorio;

    boolean GameOver = false;
    Dialog miDialog;

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
        miDialog = new Dialog(EscenarioJuego.this);

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
                if (!GameOver) {
                    contador++;
                    TvContador.setText(String.valueOf(contador));

                    IvZombie.setImageResource(R.drawable.zombieaplastado);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            IvZombie.setImageResource(R.drawable.zombie2);
                            Movimiento();
                        }
                    }, 500);
                }
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
        GameOver = true;
        MensajeGameOver ();

    }
         }.start();
     }

    private void MensajeGameOver() {

        TextView SeacaboTXT,HasmatadoTXT,NumeroTXT;
        Button JUGARDENUEVO,IRMENU,PUNTAJES;

        miDialog.setContentView(R.layout. gameover);

        SeacaboTXT= miDialog.findViewById (R. id. SeacaboTXT);
        HasmatadoTXT = miDialog.findViewById (R.id. HasmatadoTXT);
        NumeroTXT= miDialog.findViewById (R. id. NumeroTXT);

        JUGARDENUEVO = miDialog.findViewById (R.id. JUEGARDENUEVO);
        IRMENU = miDialog.findViewById (R. id. IRMENU);
        PUNTAJES = miDialog.findViewById (R. id. PUNTAJES);

        String zombies = String.valueOf(contador);
        NumeroTXT.setText(zombies);
        miDialog.show();

        JUGARDENUEVO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( EscenarioJuego.this,  "JUGAR DE NUEVO", Toast.LENGTH_SHORT).show();
            }
        });
        IRMENU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( EscenarioJuego.this,  "MENU", Toast.LENGTH_SHORT).show();
            }
        });
        PUNTAJES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( EscenarioJuego.this,  "PUNTAJES", Toast.LENGTH_SHORT).show();
            }
        });



        }
}