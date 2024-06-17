package com.example.termiar.Donacion;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termiar.Adapter.DonacionAdapter;
import com.example.termiar.Adapter.FormularioAdapter;
import com.example.termiar.Formulario.Formulario;
import com.example.termiar.Network.RetrofitFactory;
import com.example.termiar.R;
import com.example.termiar.Servicios.DonadoresService;
import com.example.termiar.Servicios.FormularioService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DonacionMostar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donacion_mostar);
        mostarDonacion();
    }

    private void mostarDonacion() {
        Retrofit retrofit5 = RetrofitFactory.build();
        DonadoresService servicio4 = retrofit5.create(DonadoresService.class);
        Call<List<Donacion_Const>> call4 = servicio4.GetDonacion();
        call4.enqueue(new Callback<List<Donacion_Const>>() {
            @Override
            public void onResponse(Call<List<Donacion_Const>> call, Response<List<Donacion_Const>> response) {
                if (response.isSuccessful()) {
                    Log.e("CONEXION", "CONEXION EXITOSA");
                }else{
                    Log.i("CONEXION", "CONEXION Correcta");
                Log.i("CONEXION","Si muestra datos");

                   List<Donacion_Const>GetDon = response.body();
                    DonacionAdapter adapter = new DonacionAdapter(GetDon);
                    RecyclerView rv1 = findViewById(R.id.forminvil);
                    rv1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv1.setHasFixedSize(true);
                    rv1.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Donacion_Const>> call, Throwable t) {
                Log.e("CONEXION","NO hay conexion al MOCK");
            }
        });
        }
}