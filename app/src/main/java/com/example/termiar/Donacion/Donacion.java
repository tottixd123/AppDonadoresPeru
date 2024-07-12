package com.example.termiar.Donacion;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.termiar.Activity.General.Generl;
import com.example.termiar.Activity.Reloj.TimerService;

import com.example.termiar.Network.RetrofitFactory;
import com.example.termiar.R;
import com.example.termiar.Servicios.DonadoresService;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Donacion extends AppCompatActivity {
    // no olvidar modificar para actualizacion
        private EditText fechadedonacion;
        private EditText lugardedonacion;
        private EditText horadedonacion;
        private Button boton_don;
        private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        private SharedPreferences prefs;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_donacion);
            prefs = getSharedPreferences("TimerPrefs", MODE_PRIVATE);
            fechadedonacion = findViewById(R.id.fecha);
            lugardedonacion = findViewById(R.id.lugar);
            horadedonacion = findViewById(R.id.hora);
            boton_don = findViewById(R.id.btn_endon);

            boton_don.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(validateFields()) {
                        guardarDonador();
                    } else {
                        Toast.makeText(Donacion.this, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        private boolean validateFields() {
            return !fechadedonacion.getText().toString().isEmpty() &&
                    !lugardedonacion.getText().toString().isEmpty() &&
                    !horadedonacion.getText().toString().isEmpty();
        }

        private void guardarDonador() {
            if (isTimerRunning()) {
                Toast.makeText(this, "El período de donación está en curso", Toast.LENGTH_SHORT).show();
                return;
            }
            Retrofit retrofitDon = RetrofitFactory.build();
            DonadoresService serviceDon = retrofitDon.create(DonadoresService.class);
            Donacion_Const donaciones = new Donacion_Const();
            donaciones.setFechadedonacion(fechadedonacion.getText().toString());
            donaciones.setLugardedonacion(lugardedonacion.getText().toString());
            donaciones.setHoradedonacion(horadedonacion.getText().toString());
            Call<Donacion_Const> call = serviceDon.PostCrearDonacion(donaciones);
            call.enqueue(new Callback<Donacion_Const>() {
                @Override
                public void onResponse(Call<Donacion_Const> call, Response<Donacion_Const> response) {
                    if (response.isSuccessful()) {
                        Log.i("CONEXION", new Gson().toJson(response.body()));
                        startTimer(); // Inicia el temporizador solo después de una respuesta exitosa
                        Toast.makeText(Donacion.this, "Donación guardada exitosamente", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Donacion.this, Generl.class));
                        finish();
                    } else {
                        Toast.makeText(Donacion.this, "Error al guardar la donación", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Donacion_Const> call, Throwable t) {
                    Log.e("CONEXION", "No hay conexión");
                    Toast.makeText(Donacion.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                }
            });
        }
        private boolean isTimerRunning() {
            SharedPreferences prefs = getSharedPreferences("timer_prefs", MODE_PRIVATE);
            long startTime = prefs.getLong("StartTime", 0);
            long currentTime = System.currentTimeMillis();
            return startTime != 0 && (currentTime - startTime) < (30 * 24 * 60 * 60 * 1000L); // 30 días en milisegundos
        }

        private void startTimer() {
            long startTime = System.currentTimeMillis();
            SharedPreferences prefs = getSharedPreferences("timer_prefs", MODE_PRIVATE);
            prefs.edit().putLong("StartTime", startTime).apply();

            Intent serviceIntent = new Intent(this, TimerService.class);
            serviceIntent.putExtra("StartTime", startTime);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent);
            } else {
                startService(serviceIntent);
            }
        }
}