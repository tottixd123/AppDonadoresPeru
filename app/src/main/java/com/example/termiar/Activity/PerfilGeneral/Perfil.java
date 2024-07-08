package com.example.termiar.Activity.PerfilGeneral;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.termiar.Activity.General.Generl;
import com.example.termiar.Activity.Mapa.Map_donaciones;
import com.example.termiar.Donacion.UltimaDonacion;
import com.example.termiar.Notificaciones.Notifi;
import com.example.termiar.R;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);
        Boton_regresar();
        Boton_centros();
        Boton_salir();
        BotonUltima();
    }
    private void Boton_salir() {
        ConstraintLayout botonSalir = findViewById(R.id.btn_salir);
        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog show = new AlertDialog.Builder(Perfil.this)
                        .setTitle("Confirmar salida")
                        .setMessage("¿Estás seguro de que deseas salir?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity(); // Cierra todas las actividades
                                System.exit(0);   // Cierra la aplicación
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        }

    private void Boton_centros() {
        ConstraintLayout Boton_centros = findViewById(R.id.btn_centrodedonacion);
        Boton_centros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Perfil.this, Map_donaciones.class));
            }
        });
    }

    private void Boton_regresar() {
        ConstraintLayout Boton_regresar = findViewById(R.id.atrasperfinl);
        Boton_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Perfil.this, Generl.class));
            }
        });
    }

    private void BotonUltima(){
        ConstraintLayout BotonUltima = findViewById(R.id.btn_ultimaD);
        BotonUltima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Perfil.this, UltimaDonacion.class));
            }
        });
    }

}

