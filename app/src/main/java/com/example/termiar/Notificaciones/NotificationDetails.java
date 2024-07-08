package com.example.termiar.Notificaciones;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.termiar.Activity.General.Generl;
import com.example.termiar.R;

public class NotificationDetails {
    private static final String CHANNEL_ID = "CANAL_ID";
    private static final int NOTIFICATION_ID = 1;
    private static final int MY_PERMISSIONS_REQUEST_NOTIFICATIONS = 123;

    private Context context;

    public NotificationDetails(Context context) {
        this.context = context;
        createNotificationChannel();
    }

    public void showNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS)
                    == PackageManager.PERMISSION_GRANTED) {
                buildAndSendNotification();
            } else {
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    ActivityCompat.requestPermissions(activity,
                            new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                            MY_PERMISSIONS_REQUEST_NOTIFICATIONS);
                } else {
                    Log.e("NotificationDetails", "Context must be an instance of Activity to request permissions");
                }
            }
        } else {
            buildAndSendNotification();
        }
    }

    private void buildAndSendNotification() {
        Intent intent = new Intent(context, Generl.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.btn_1)
                .setContentTitle("Donadores Peru")
                .setContentText("Activaste notificaciones")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        } else {
            Log.w("NotificationDetails", "Permiso de notificación no concedido");
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Canal de Notificaciones";
            String description = "Descripción del canal de notificaciones";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}