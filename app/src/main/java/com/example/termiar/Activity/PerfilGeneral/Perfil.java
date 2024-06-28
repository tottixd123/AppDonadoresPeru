package com.example.termiar.Activity.PerfilGeneral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.termiar.Activity.General.Generl;
import com.example.termiar.R;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);
        Boton_regresar();
        Boton_centros();
        }

    private void Boton_centros() {

    }

    private void Boton_regresar() {
        ImageView Boton_regresar = findViewById(R.id.atrasperfinl);
        Boton_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Perfil.this, Generl.class));
            }
        });
    }
}



