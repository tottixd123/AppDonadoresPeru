package com.example.termiar.Activity.Reloj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.termiar.R;

public class Cronometro extends AppCompatActivity {
    private TextView timerTextView;
    private BroadcastReceiver timerReceiver;
    private boolean isReceiverRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        timerTextView = findViewById(R.id.textoTiempoArriba);
        //receptor regristrado

        registerTimerReceiver();
        //verificar si el temporizador ya esta en ejecuion
        if(isTimerRunning()){
            updateTimerDisplay(getRemainingTime());
        }else {
            timerTextView.setText("¡Contador finalizado!");
        }
      /*  Intent serviceIntent=new Intent(this,TimerService.class);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            startForegroundService(serviceIntent);
        }else{
            startTimerService();
        }
        registerTimerReceiver();*/
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerTimerReceiver();
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterTimerReceiver();
    }
    private boolean isTimerRunning() {
        SharedPreferences prefs = getSharedPreferences("timer_prefs", MODE_PRIVATE);
        long startTime = prefs.getLong("StartTime", 0);
        long currentTime = System.currentTimeMillis();
        return startTime != 0 && (currentTime - startTime) < (30 * 24 * 60 * 60 * 1000L);
    }
    private long getRemainingTime() {
        SharedPreferences prefs = getSharedPreferences("timer_prefs", MODE_PRIVATE);
        long startTime = prefs.getLong("StartTime", 0);
        long currentTime = System.currentTimeMillis();
        long totalTime = 30 * 24 * 60 * 60 * 1000L; // 30 días en milisegundos
        return Math.max(0, totalTime - (currentTime - startTime));
    }

    /*private void startTimerService() {
        Intent serviceIntent = new Intent(this, TimerService.class);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent);
            } else {
                startService(serviceIntent);
            }
        }catch (Exception e){
            e.printStackTrace();
            // Considerar manejar este error, tal vez mostrando un mensaje al usuario
        }
    }*/

    private void registerTimerReceiver() {
        if (!isReceiverRegistered) {
            timerReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if ("timer_update".equals(intent.getAction())) {
                        long timeLeft = intent.getLongExtra("time_left", 0);
                        updateTimerDisplay(timeLeft);
                    } else if ("timer_finish".equals(intent.getAction())) {
                        timerTextView.setText("¡Contador finalizado!");
                    }
                }
            };

            IntentFilter filter = new IntentFilter();
            filter.addAction("timer_update");
            filter.addAction("timer_finish");
            registerReceiver(timerReceiver, filter);
            isReceiverRegistered = true;
        }
    }
    private void unregisterTimerReceiver() {
        if (isReceiverRegistered && timerReceiver != null) {
            try {
                unregisterReceiver(timerReceiver);
                isReceiverRegistered = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void updateTimerDisplay(long timeLeft) {
        long days = timeLeft / (24 * 60 * 60 * 1000);
        long hours = (timeLeft % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
        long minutes = (timeLeft % (60 * 60 * 1000)) / (60 * 1000);
        long seconds = (timeLeft % (60 * 1000)) / 1000;

        String timeString = String.format("%d días, %02d:%02d:%02d", days, hours, minutes, seconds);
        timerTextView.setText(timeString);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("timerText", timerTextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            timerTextView.setText(savedInstanceState.getString("timerText", ""));
        }
    }

}