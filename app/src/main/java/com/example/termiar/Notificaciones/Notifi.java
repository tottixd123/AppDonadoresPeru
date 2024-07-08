package com.example.termiar.Notificaciones;

import static androidx.core.content.ContextCompat.startActivity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;


import com.example.termiar.Activity.General.Generl;
import com.example.termiar.Activity.Mapa.Map_donaciones;
import com.example.termiar.Activity.PerfilGeneral.Perfil;
import com.example.termiar.Formulario.Formulario_Registro;
import com.example.termiar.Formulario.MostarFormularios;
import com.example.termiar.R;

public class Notifi extends AppCompatActivity {

    /*
=======
    private static final int MY_PERMISSIONS_REQUEST_VIBRATE = 1;
>>>>>>> RamaFlaca

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notifiaciones);
        // Verificar y solicitar permiso de vibración si es necesario
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.VIBRATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.VIBRATE},
                    MY_PERMISSIONS_REQUEST_VIBRATE);
        } else {
            // Crear y mostrar la notificación si el permiso ya está otorgado
        }

        BotonInicio();
        BotonPerfil();
        Button_formu();
        Button_lugar();
    }


    private void BotonInicio() {
        LinearLayout Inicio = findViewById(R.id.inicioBtn);
        Inicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifi.this, Generl.class));
            }
        });
    }

    private void Botton_mostrar() {
        LinearLayout historialbtn=findViewById(R.id.historialbtn);
        historialbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifi.this, MostarFormularios.class));
            }
        });
    }

    private void Button_lugar(){
        LinearLayout lugarBtn=findViewById(R.id.lugarBtn);
        lugarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifi.this, Map_donaciones.class));
            }
        });
    }
    private void Button_formu(){
        LinearLayout configuraicon=findViewById(R.id.configuraicon);
        configuraicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifi.this, Formulario_Registro.class));
            }
        });
    }
    private void BotonPerfil() {
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notifi.this, Perfil.class));
            }
        });
    }
<<<<<<< HEAD
    */

}



