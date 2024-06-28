package com.example.termiar.Activity.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.termiar.Activity.General.Generl;
import com.example.termiar.Formulario.Formulario_Registro;
import com.example.termiar.R;

public class MainActivity extends AppCompatActivity {
    public AppCompatButton go_boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go_boton=findViewById(R.id.go_boton);
        go_boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Formulario_Registro.class));
            }
        });
        }
    }
