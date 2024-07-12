package com.example.termiar.Donacion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.termiar.Activity.General.Generl;
import com.example.termiar.Activity.PerfilGeneral.Perfil;
import com.example.termiar.Activity.PerfilGeneral.PerfilUltimo;
import com.example.termiar.Formulario.Formulario;
import com.example.termiar.Network.RetrofitFactory;
import com.example.termiar.R;
import com.example.termiar.Servicios.DonadoresService;
import com.example.termiar.Servicios.FormularioService;
import com.google.gson.Gson;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UltimaDonacion extends AppCompatActivity {

    private TextView fechadedonacion, lugardedonacion, horadedonacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ultima_donacion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Boton_regresar();
        fechadedonacion = findViewById(R.id.fechadedonacion);
        lugardedonacion = findViewById(R.id.lugardedonacion);
        horadedonacion = findViewById(R.id.horadedonacion);

        MostrarUltimaDonacion();

    }

    private void Boton_regresar() {
        ConstraintLayout regresar=findViewById(R.id.regresarperf);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UltimaDonacion.this, Perfil.class));
            }
        });
    }


    private void MostrarUltimaDonacion() {
        Retrofit retrofit = RetrofitFactory.buiilFecha();
        DonadoresService servicio1 = retrofit.create(DonadoresService.class);

        Call<List<Donacion_Const>> call1 = servicio1.getRecords(1, "desc");
        call1.enqueue(new Callback<List<Donacion_Const>>() {
            @Override
            public void onResponse(Call<List<Donacion_Const>> call, Response<List<Donacion_Const>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("ULTIMACONEXION", "SI MUESTRA DATOS");
                    Log.i("ULTIMACONEXION", new Gson().toJson(response.body()));

                    List<Donacion_Const> records = response.body();

                    Collections.sort(records, new Comparator<Donacion_Const>() {
                        @Override
                        public int compare(Donacion_Const donacionConst, Donacion_Const t1) {
                            return 0;
                        }
                    });

                    if (!records.isEmpty()) {
                        Donacion_Const latestRecord = records.get(records.size() - 1);
                        fechadedonacion.setText(latestRecord.getFechadedonacion().toString());
                        lugardedonacion.setText(latestRecord.getLugardedonacion());
                        horadedonacion.setText(latestRecord.getHoradedonacion());
                    }
                } else {
                    Log.e("ULTIMACONEXION", "Error de la APP " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Donacion_Const>> call, Throwable t) {
                Log.e("ULTIMACONEXION", "NO hay conexion al MOCK: " + t.getMessage(), t);
            }
        });
    }

}
