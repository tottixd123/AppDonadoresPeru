package com.example.termiar.Donacion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.termiar.Activity.General.Generl;
import com.example.termiar.Formulario.Formulario;
import com.example.termiar.Formulario.Formulario_Registro;
import com.example.termiar.Network.RetrofitFactory;
import com.example.termiar.R;
import com.example.termiar.Servicios.DonadoresService;
import com.example.termiar.Servicios.FormularioService;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Donacion extends AppCompatActivity {
    private EditText fechadedonacion;
    private EditText lugardedonacion;
    private EditText horadedonacion;
    private Button boton_don;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donacion);
        fechadedonacion = findViewById(R.id.fecha);
        lugardedonacion = findViewById(R.id.lugar);
        horadedonacion = findViewById(R.id.hora);
        boton_don=findViewById(R.id.btn_endon);
        boton_don.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                guardarDonador();
            }
        });
    }
    private void guardarDonador() {
        Retrofit retrofitDon= RetrofitFactory.build();
        DonadoresService serviceDon=retrofitDon.create(DonadoresService.class);
        Donacion_Const donaciones = new Donacion_Const();
        // Convertir la fecha de string a Date
        // Establecer los valores en donaciones
        donaciones.setFechadedonacion(fechadedonacion.getText().toString());
        donaciones.setLugardedonacion(lugardedonacion.getText().toString());
        donaciones.setHoradedonacion(horadedonacion.getText().toString());
        Call<Donacion_Const> call = serviceDon.PostCrearDonacion(donaciones);
        call.enqueue(new Callback<Donacion_Const>() {

            @Override
            public void onResponse(Call<Donacion_Const> call, Response<Donacion_Const> response) {
                Log.i("CONEXION", new Gson().toJson(response.body()));
                startActivity(new Intent(Donacion.this, Generl.class));
            }

            @Override
            public void onFailure(Call<Donacion_Const> call, Throwable t) {
                Log.e("CONEXION","No hay conexion");
            }
        });
    }
}