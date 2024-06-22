package com.example.termiar.Activity.Reloj;


import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.termiar.R;

public class Cronometro extends AppCompatActivity {

    private TextView contador , textoTiempo;
    private TimerReceiver timerReceiver;

    private String UltimoTexto = ""; // Para almacenar el último texto mostrado
    Button pruebaInicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cronometro);

        contador = findViewById(R.id.cronometroGeneral);
        textoTiempo= findViewById(R.id.textoTiempoArriba);
        EmpiezaLaPrueba();

                /*
        pruebaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmpiezaLaPrueba();
            }
        });
        */


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public class TimerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("timerTick".equals(action)) {
                long millisUntilFinished = intent.getLongExtra("countdown", 0);
                String timeLeftFormatted = intent.getStringExtra("timeLeftFormatted");
                animacionFormato(timeLeftFormatted); // Método para animar o actualizar la interfaz
            } else if ("timerFinish".equals(action)) {
                // Asegúrate de que textoTiempo y contador no sean nulos antes de actualizar
                if (textoTiempo != null && contador != null) {
                    textoTiempo.setText("Apto para realizar una Donación");
                    contador.setText("Tiempo Finalizado");
                    MediaPlayer alarma = MediaPlayer.create(context, R.raw.sonido);
                    alarma.start();
                }
            }
        }
    }

    private void EmpiezaLaPrueba() {
        timerReceiver = new TimerReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("timerTick");
        intentFilter.addAction("timerFinish");
        registerReceiver(timerReceiver, intentFilter);

        // Iniciar el servicio del temporizador
        Intent serviceIntent = new Intent(this, TimerService.class);
        startService(serviceIntent);
    }


    private void animacionFormato(String TextoNuevo){

        if(!TextoNuevo.equals(UltimoTexto)){
            // Crear una animación de desplazamiento hacia abajo (traslación Y)
            ObjectAnimator translateY = ObjectAnimator.ofFloat(contador, "translationY", 0f, 50f);
            translateY.setDuration(1); // duración de la animación en milisegundos

            translateY.addListener(new android.animation.AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    contador.setText(TextoNuevo);
                    // Restaurar la posición original
                    ObjectAnimator.ofFloat(contador, "translationY", 50f, 0f).setDuration(0).start();
                }
            });
            translateY.start();
            UltimoTexto = TextoNuevo;
        }
    }

    private String FormatVoidxD(long milisegs){
        int dias = (int) (milisegs / (1000 * 60 * 60 * 24));
        int horas = (int) ((milisegs / (1000 * 60 * 60)) % 24);
        int minutos = (int) ((milisegs / (1000 * 60)) % 60);
        int segundos = (int) (milisegs / 1000) % 60;

        if (milisegs >= 86400000) { // Si el tiempo es mayor o igual a 24 horas
            return String.format("%0ddías", dias);
        } else if (milisegs >= 3600000) { // Si el tiempo es mayor o igual a 60 minutos (1 hora)
            return String.format("%02dh %02dm %02ds", horas, minutos, segundos);
        } else if(milisegs >= 60000) {
            return String.format("%02dm %02ds", minutos, segundos);
        } else{
            return String.format("%02ds", segundos);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Desregistrar el receptor de difusión
        unregisterReceiver(timerReceiver);
    }

}