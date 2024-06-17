package com.example.termiar.Notificaciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.termiar.Activity.General.Generl;
import com.example.termiar.Activity.Mapa.Map_donaciones;
import com.example.termiar.Activity.PerfilGeneral.Perfil;
import com.example.termiar.Formulario.Formulario_Registro;
import com.example.termiar.Formulario.MostarFormularios;
import com.example.termiar.R;

public class Notifi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notifiaciones);
        BotonInicio();
        BotonPerfil();
        Button_formu();
        Button_lugar();

    }
    private void BotonInicio() {
        LinearLayout Inicio =findViewById(R.id.inicioBtn);
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
}