package com.example.termiar.Activity.VerMasNoticias;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termiar.Activity.General.Generl;
import com.example.termiar.Activity.Mapa.Map_donaciones;
import com.example.termiar.Activity.PerfilGeneral.Perfil;
import com.example.termiar.Adapter.TrendsAdapter;
import com.example.termiar.Domain.TrendSDomain;
import com.example.termiar.Formulario.Formulario_Registro;
import com.example.termiar.Network.RetrofitFactory;
import com.example.termiar.R;
import com.example.termiar.Servicios.NoticiaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MasNoticias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mas_noticias);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initVerMas();
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
                startActivity(new Intent(MasNoticias.this, Generl.class));
            }
        });
    }

    private void Button_lugar(){
        LinearLayout lugarBtn=findViewById(R.id.lugarBtn);
        lugarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MasNoticias.this, Map_donaciones.class));
            }
        });
    }

    private void Button_formu(){
        LinearLayout configuraicon=findViewById(R.id.configuraicon);
        configuraicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MasNoticias.this, Formulario_Registro.class));
            }
        });
    }
    private void BotonPerfil() {
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MasNoticias.this, Perfil.class));
            }
        });
    }


    private void initVerMas(){

        Retrofit retrofit = RetrofitFactory.build2();

        NoticiaService servicio1 = retrofit.create(NoticiaService.class);
        Call<List<TrendSDomain>> call1 = servicio1.GetNoticias();

        call1.enqueue(new Callback<List<TrendSDomain>>() {
            @Override
            public void onResponse(Call<List<TrendSDomain>> call, Response<List<TrendSDomain>> response) {
                if (!response.isSuccessful()){
                    Log.e("NOTICIOSA","Error de la APP");
                }else {
                    Log.i("NOTICIOSA","Conexion Correcta");
                    //Log.i("NOTICIOSA",new Gson().toJson(response.body()));
                    Log.i("NOTICIOSA","SI MUESTRA DATOS");

                    List<TrendSDomain>GetFormu = response.body();

                    TrendsAdapter adapter = new TrendsAdapter(GetFormu);

                    RecyclerView rv = findViewById(R.id.RecyVerMas);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);




                }
            }

            @Override
            public void onFailure(Call<List<TrendSDomain>> call, Throwable t) {
                //NO hay conexion al MOCK
                Log.e("CONEXION","NO hay conexion al MOCK");
            }
        });

    }


}