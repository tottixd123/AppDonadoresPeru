package com.example.termiar.Notificaciones;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.example.termiar.Activity.General.Generl;
import com.example.termiar.R;

public class Notifi extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_VIBRATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notifiaciones);
        // Crea una instancia de NotificationDetails
        NotificationDetails notificationDetails = new NotificationDetails(this);
        notificationDetails.showNotification();
        // Crear el canal de notificaciones
        createNotificationChannel();
        // Verificar y solicitar permiso de vibración y notificaciones si es necesario
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, MY_PERMISSIONS_REQUEST_VIBRATE);
        } else {
            // Crear y mostrar la notificación si el permiso ya está otorgado
            showNotification();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_VIBRATE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showNotification();
            } else {
                Toast.makeText(this, "Permiso de vibración denegado", Toast.LENGTH_SHORT).show();
            }
            // Regresar a la pantalla anterior
            finish();
        }
    }

    private void showNotification() {
        try {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CANAL_ID")
                    .setSmallIcon(R.drawable.btn_1)
                    .setContentTitle("Donadores Peru")
                    .setContentText("Notificaciones activadas")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);

            Intent intent = new Intent(this, Generl.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    == PackageManager.PERMISSION_GRANTED) {
                notificationManager.notify(1, builder.build());
            } else {
                Toast.makeText(this, "Permiso de notificaciones no otorgado", Toast.LENGTH_SHORT).show();
            }
        } catch (SecurityException e) {
            Toast.makeText(this, "Error al enviar la notificación: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Donadores";
            String description = "Descripción del canal de notificaciones";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CANAL_ID", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
