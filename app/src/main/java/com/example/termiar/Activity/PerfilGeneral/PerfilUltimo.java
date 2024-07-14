package com.example.termiar.Activity.PerfilGeneral;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.termiar.Donacion.Donacion_Const;
import com.example.termiar.Formulario.Formulario;
import com.example.termiar.Network.RetrofitFactory;
import com.example.termiar.R;
import com.example.termiar.Servicios.DonadoresService;
import com.example.termiar.Servicios.FormularioService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PerfilUltimo extends AppCompatActivity {

    private TextView usuario, talla, peso,sexo,tipoSangre23,fechaUltimaDonacion65;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_ultimo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usuario = findViewById(R.id.usuario);
        talla = findViewById(R.id.talla);
        peso = findViewById(R.id.peso);
        sexo = findViewById(R.id.sexo);
        tipoSangre23 = findViewById(R.id.tipoSangre23);
        fechaUltimaDonacion65 = findViewById(R.id.fechaUltimaDonacion65);

        MostrarPerfilUltimo();

    }

    private void MostrarPerfilUltimo(){
        Retrofit retrofit = RetrofitFactory.buiilFecha();
        FormularioService formu = retrofit.create(FormularioService.class);
        Call<List<Formulario>> call1 = formu.getRecordsFormu(1, "desc");
        call1.enqueue(new Callback<List<Formulario>>() {
            @Override
            public void onResponse(Call<List<Formulario>> call, Response<List<Formulario>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("FORMUULTIMO", "SI MUESTRA DATOS");
                    Log.i("FORMUULTIMO", new Gson().toJson(response.body()));

                    List<Formulario> records = response.body();
                    if (!records.isEmpty()) {
                        Formulario latestRecord = records.get(records.size() - 1);
                        usuario.setText(latestRecord.getUsuario());
                        talla.setText(Double.toString(latestRecord.getTalla()));
                        peso.setText(Double.toString(latestRecord.getPeso()));
                        sexo.setText(latestRecord.getSexo());
                        tipoSangre23.setText(latestRecord.getTipoSangre());
                        fechaUltimaDonacion65.setText(latestRecord.getFechaUltimaDonacion());
                    }
                } else {
                    Log.e("FORMUULTIMO", "Error de la APP " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Formulario>> call, Throwable t) {
                Log.e("FORMUULTIMO", "NO hay conexion al MOCK: " + t.getMessage(), t);
            }
        });


    }





}