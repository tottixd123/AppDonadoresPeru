package com.example.termiar.Activity.Reloj;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.example.termiar.R;

public class TimerService extends Service {
    private static final long TOTAL_TIME = 30 * 24 * 60 * 60 * 1000L; // 30 días en milisegundos
    private static final long COUNTDOWN_INTERVAL = 1000L; // Actualizar cada segundo
    private static final long NOTIFICATION_UPDATE_INTERVAL = 60000L; // Actualizar notificación cada minuto
    private static final String CHANNEL_ID = "TimerChannel";
    private static final int NOTIFICATION_ID = 1;
    private static final String PREF_NAME = "TimerPrefs";
    private static final String PREF_END_TIME = "EndTime";

    private CountDownTimer tiempo;
    private NotificationManagerCompat notificationManager;
    private long lastNotificationUpdate = 0;
    private long endTime;

    public static final String ACTION_TIMER_FINISHED="com.example.terminar.ACTION_TIMER_FINISHED";

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = NotificationManagerCompat.from(this);
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (isTimerRunning()) {
            // Si ya hay un temporizador en ejecución, no inicies uno nuevo
            return START_NOT_STICKY;
        }
        long startTime = intent.getLongExtra("StartTime", System.currentTimeMillis());
        endTime = startTime + TOTAL_TIME;
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        prefs.edit().putLong(PREF_END_TIME, endTime).apply();
        boolean notificationsEnabled=prefs.getBoolean("notifications_enabled",true);
        if(notificationsEnabled){
            startForeground(NOTIFICATION_ID, createNotification("Cuenta regresiva iniciada",
                    "La cuenta regresiva ha comenzado"));
        }else{
            //iniciar el servicio sin notifiaciaones visibles
            startForeground(NOTIFICATION_ID,createSilentNotification());
        }
        startTimer();
        return START_STICKY;

        /*  SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        endTime=System.currentTimeMillis() + TOTAL_TIME;
        prefs.edit().putLong(PREF_END_TIME, endTime).apply();
        // endTime = prefs.getLong(PREF_END_TIME, 0);
        //if(endTime == 0 || System.currentTimeMillis() > endTime){
            //Iniciar nuevo temporizador
          //  endTime = System.currentTimeMillis() + TOTAL_TIME;
          //  prefs.edit().putLong(PREF_END_TIME, endTime).apply();
       // }
        startForeground(NOTIFICATION_ID, createNotification("Cuenta regresiva iniciada",
                "La cuenta regresiva ha comenzado"));
        startTimer();
        return START_STICKY; // El servicio se reiniciará si el sistema lo mata
    */
    }
    private boolean isTimerRunning() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        long endTime = prefs.getLong(PREF_END_TIME, 0);
        return System.currentTimeMillis() < endTime;
    }
    private Notification createSilentNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(
                    "silent_channel",
                    "Silent Notications",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        return new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Cuenta regresiva")
                .setContentText("La cuenta regresiva ha comenzado")
                .setSmallIcon(R.drawable.notification)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
    }

    private void startTimer() {
        long timeLeft = endTime - System.currentTimeMillis();
        if (timeLeft <= 0) {
            onTimerFinish();
            return;
        }
        tiempo = new CountDownTimer(timeLeft, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(System.currentTimeMillis() - lastNotificationUpdate >= NOTIFICATION_UPDATE_INTERVAL){
                    updateNotification(FormatVoidxD(millisUntilFinished));
                    lastNotificationUpdate = System.currentTimeMillis();
                }
                broadcastUpdate(millisUntilFinished);
            }
            @Override
            public void onFinish() {
                onTimerFinish();
            }
        }.start();
    }
    private void onTimerFinish() {
        updateNotification("Cuenta regresiva finalizada");
        broadcastFinish();
        getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().remove(PREF_END_TIME).apply();
        //Enviar broadcast cuando el timer termine
        Intent finishIntent = new Intent(ACTION_TIMER_FINISHED);
        sendBroadcast(finishIntent);
        stopSelf();
    }
    @SuppressLint("MissingPermission")
    private void updateNotification(String timeLeftFormatted) {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean notificationsEnabled = prefs.getBoolean("notifications_enabled", true);
        if (!notificationsEnabled) {
            return;//No mostar notificaionn si estan descativados
        }
        try {
            Notification notification = createNotification("Cuenta regresiva", timeLeftFormatted);
            notificationManager.notify(NOTIFICATION_ID, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Notification createNotification(String title,String content){
        Intent notificationIntent= new Intent(this, Cronometro.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_IMMUTABLE);
        return new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.notification)
                .setContentIntent(pendingIntent)
                .build();
    }
    private void broadcastUpdate(long millisUntilFinished) {
        Intent intent = new Intent("timer_update");
        intent.putExtra("time_left", millisUntilFinished);
        sendBroadcast(intent);
    }
    private void broadcastFinish() {
        Intent intent = new Intent("timer_finish");
        sendBroadcast(intent);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel= new NotificationChannel(
                    CHANNEL_ID,
                    "Timer Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (tiempo != null){
            tiempo.cancel();
        }
    }

    private String FormatVoidxD(long milisegs){
        long dias = milisegs / (24 * 60 * 60 * 1000);
        long horas = (milisegs % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
        long minutos = (milisegs % (60 * 60 * 1000)) / (60 * 1000);
        long segundos = (milisegs % (60 * 1000)) / 1000;
        return String.format("%d días, %02d:%02d:%02d", dias, horas, minutos, segundos);
    }
}