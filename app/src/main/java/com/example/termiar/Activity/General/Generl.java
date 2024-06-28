package com.example.termiar.Activity.General;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termiar.Activity.PerfilGeneral.Perfil;
import com.example.termiar.Activity.Reloj.Cronometro;
import com.example.termiar.Activity.VerMasNoticias.MasNoticias;
import com.example.termiar.Adapter.TrendsAdapter;
import com.example.termiar.Domain.TrendSDomain;
import com.example.termiar.Donacion.Donacion;
import com.example.termiar.Donacion.DonacionMostar;
import com.example.termiar.Formulario.Formulario_Registro;
import com.example.termiar.Formulario.MostarFormularios;
import com.example.termiar.Activity.Mapa.Map_donaciones;

import com.example.termiar.Network.RetrofitFactory;

import com.example.termiar.Notificaciones.Notifi;

import com.example.termiar.R;
import com.example.termiar.Servicios.NoticiaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Generl extends AppCompatActivity {
    private RecyclerView.Adapter adapterTrendsList;
    private RecyclerView recyclerViewTrends;
    private static final int MY_PERMISSIONS_REQUEST_VIBRATE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_generl);

        initRecyclearView();
        VerMasMostrar();
        BotonInicio();
        BotonPerfil();
        //Button_formu();
        Button_lugar();
        Botton_mostrar();
        Reloj();
        boton_notificaciones();

        boton_donacione();
        donaciones_formun();


    }

    private void donaciones_formun() {
        TextView donaciones_formun=findViewById(R.id.btn_donadores);
        donaciones_formun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, Donacion.class));
            }
        });
    }

    private void boton_donacione() {
        LinearLayout formulariosVer=findViewById(R.id.formulariosVer);
        formulariosVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, DonacionMostar.class));
            }
    });
    }
    private void boton_notificaciones() {
        ImageView boton_notificaciones = findViewById(R.id.button_notificaciones);
        boton_notificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, Notifi.class));
            }
        });
    }


    private void Reloj() {
        LinearLayout Reloj=findViewById(R.id.Reloj);
        Reloj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, Cronometro.class));
        }
        });
    }

    private void BotonInicio() {
        LinearLayout Inicio =findViewById(R.id.inicioBtn);
        Inicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, Generl.class));
            }
        });
    }

    private void Botton_mostrar() {
        LinearLayout historialbtn=findViewById(R.id.historialbtn);
        historialbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, MostarFormularios.class));
            }
        });
    }

    private void Button_lugar(){
        LinearLayout lugarBtn=findViewById(R.id.lugarBtn);
        lugarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, Map_donaciones.class));
            }
        });
    }
     private void Button_formu(){
        LinearLayout configuraicon=findViewById(R.id.configuraicon);
         configuraicon.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Generl.this, Formulario_Registro.class));
             }
         });
     }

     private void VerMasMostrar(){
        Button verMasBtn = findViewById(R.id.buttVerMas);
        verMasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, MasNoticias.class));
            }
        });

     }

    private void BotonPerfil() {
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, Perfil.class));
            }
        });
    }
    private void initRecyclearView(){

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

                    RecyclerView rv = findViewById(R.id.view1);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
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