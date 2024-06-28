package com.example.termiar.Activity.Reloj;


import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;

public class TimerService extends Service {

    private CountDownTimer tiempo;
    private static final long InicioTiempoMili = 86460000; // 86400000 * 2 - 2 días en milisegundos

    @Override
    public void onCreate() {
        super.onCreate();
        startTimer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY; // El servicio se reiniciará si el sistema lo mata
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startTimer() {
        tiempo = new CountDownTimer(InicioTiempoMili, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // Calcular días, horas, minutos y segundos
                int dias = (int) (millisUntilFinished / (1000 * 60 * 60 * 24));
                int horas = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                int minutos = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int segundos = (int) (millisUntilFinished / 1000) % 60;

                // Formatear el tiempo restante
                String timeLeftFormatted;
                if (millisUntilFinished >= 86400000) { // Si el tiempo es mayor o igual a 24 horas
                    timeLeftFormatted = String.format("%ddías", dias);
                } else if (millisUntilFinished >= 3600000) { // Si el tiempo es mayor o igual a 1 hora
                    timeLeftFormatted = String.format("%02dh %02dm %02ds", horas, minutos, segundos);
                } else if (millisUntilFinished >= 60000) { // Si el tiempo es mayor o igual a 1 minuto
                    timeLeftFormatted = String.format("%02dm %02ds", minutos, segundos);
                } else { // Menos de 1 minuto
                    timeLeftFormatted = String.format("%02ds", segundos);
                }

                // Enviar una emisión local para actualizar la interfaz de usuario
                Intent intent = new Intent("timerTick");
                intent.putExtra("countdown", millisUntilFinished);
                intent.putExtra("timeLeftFormatted", timeLeftFormatted);
                sendBroadcast(intent);
            }

            @Override
            public void onFinish() {
                // Enviar una emisión local para indicar que el temporizador ha terminado
                Intent intent = new Intent("timerFinish");
                sendBroadcast(intent);
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (tiempo != null) {
            tiempo.cancel();
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
}
