package com.example.termiar.Campanas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termiar.Activity.General.Generl;
import com.example.termiar.Activity.Mapa.Map_donaciones;
import com.example.termiar.Activity.PerfilGeneral.Perfil;
import com.example.termiar.Adapter.CampanaAdapter;
import com.example.termiar.Adapter.TrendsAdapter;
import com.example.termiar.Domain.TrendSDomain;
import com.example.termiar.Network.RetrofitFactory;
import com.example.termiar.R;
import com.example.termiar.Servicios.CampanaService;
import com.example.termiar.Servicios.NoticiaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CampanaMostrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_campana_mostrar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InicioCamp();
        BotonInicio();
        BotonPerfil();
        Button_lugar();
        Button_formu();

    }

    private void InicioCamp(){
        Retrofit retrofit = RetrofitFactory.build2();
        CampanaService campa = retrofit.create(CampanaService.class);
        Call<List<Campana>> call1 = campa.GetCampanas();
        call1.enqueue(new Callback<List<Campana>>() {
            @Override
            public void onResponse(Call<List<Campana>> call, Response<List<Campana>> response) {
                if (!response.isSuccessful()){
                    Log.e("CAMPAÑA","Error de la APP");
                }else {
                    Log.i("CAMPAÑA","Conexion Correcta");
                    //Log.i("NOTICIOSA",new Gson().toJson(response.body()));
                    Log.i("CAMPAÑA","SI MUESTRA DATOS");
                    List<Campana>GetFormu = response.body();
                    CampanaAdapter adapter = new CampanaAdapter(GetFormu);
                    RecyclerView rv = findViewById(R.id.campanaRecy);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Campana>> call, Throwable t) {
                //NO hay conexion al MOCK
                Log.e("CAMPAÑA","NO hay conexion al MOCK");
            }
        });


    }
    private void BotonInicio() {
        LinearLayout Inicio =findViewById(R.id.inicioBtn);
        Inicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CampanaMostrar.this, Generl.class));
            }
        });
    }

    private void BotonPerfil() {
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CampanaMostrar.this, Perfil.class));
            }
        });
    }
    private void Button_lugar(){
        LinearLayout lugarBtn=findViewById(R.id.lugarBtn);
        lugarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CampanaMostrar.this, Map_donaciones.class));
            }
        });
    }
    private void Button_formu(){
        LinearLayout configuraicon=findViewById(R.id.configuraicon);
        configuraicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog show = new AlertDialog.Builder(CampanaMostrar.this)
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

}