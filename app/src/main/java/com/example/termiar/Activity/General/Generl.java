package com.example.termiar.Activity.General;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termiar.Activity.PerfilGeneral.Perfil;
import com.example.termiar.Adapter.TrendsAdapter;
import com.example.termiar.Domain.TrendSDomain;
import com.example.termiar.Formulario.Formulario_Registro;
import com.example.termiar.Formulario.MostarFormularios;
import com.example.termiar.Activity.Mapa.Map_donaciones;
import com.example.termiar.Notificaciones.Notifi;
import com.example.termiar.R;
import com.example.termiar.Activity.Reloj.CountDownTimerHelper;

import java.util.ArrayList;

public class Generl extends AppCompatActivity {
    private RecyclerView.Adapter adapterTrendsList;
    private RecyclerView recyclerViewTrends;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_generl);
        
        initRecyclearView();
        BotonInicio();
        BotonPerfil();
        Button_formu();
        Button_lugar();
        Botton_mostrar();
        Reloj();
        boton_notificaciones();
    }

    private void boton_notificaciones() {
        Button boton_notificaciones = findViewById(R.id.button_notificaciones);
        boton_notificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, Notifi.class));
            }
        });
    }


    private void Reloj() {
        LinearLayout Reloj=findViewById(R.id.Reloj);
        Reloj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, CountDownTimerHelper.class));
        }
        });
    }

    private void BotonInicio() {
        LinearLayout Inicio =findViewById(R.id.inicioBtn);
        Inicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, Generl.class));
            }
        });
    }

    private void Botton_mostrar() {
        LinearLayout historialbtn=findViewById(R.id.historialbtn);
        historialbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, MostarFormularios.class));
            }
        });
    }

    private void Button_lugar(){
        LinearLayout lugarBtn=findViewById(R.id.lugarBtn);
        lugarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, Map_donaciones.class));
            }
        });
    }
     private void Button_formu(){
        LinearLayout configuraicon=findViewById(R.id.configuraicon);
         configuraicon.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Generl.this, Formulario_Registro.class));
             }
         });
     }
    private void BotonPerfil() {
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, Perfil.class));
            }
        });
    }
    private void initRecyclearView(){
        ArrayList<TrendSDomain> items= new ArrayList<>();
        items.add(new TrendSDomain("noticia","Nota","trends"));
        items.add(new TrendSDomain("noticia1","Nota2","trends2"));
        items.add(new TrendSDomain("noticia","Nota","trends"));

        recyclerViewTrends=findViewById(R.id.view1);
        recyclerViewTrends.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapterTrendsList=new TrendsAdapter(items);
        recyclerViewTrends.setAdapter(adapterTrendsList);
    }
}