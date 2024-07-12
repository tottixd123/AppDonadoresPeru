package com.example.termiar.Activity.General;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.termiar.Activity.PerfilGeneral.Perfil;
import com.example.termiar.Activity.Reloj.Cronometro;
import com.example.termiar.Activity.Reloj.TimerService;
import com.example.termiar.Activity.VerMasNoticias.MasNoticias;
import com.example.termiar.Adapter.TrendsAdapter;
import com.example.termiar.Campanas.CampanaMostrar;
import com.example.termiar.Domain.TrendSDomain;
import com.example.termiar.Donacion.Donacion;
import com.example.termiar.Donacion.DonacionMostar;
import com.example.termiar.Activity.Mapa.Map_donaciones;

import com.example.termiar.Network.RetrofitFactory;

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
    private static final int PERMISSION_REQUEST_CODE=1;
    private TextView donaciones_formun;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_generl);
        prefs=getSharedPreferences("timer_prefs",MODE_PRIVATE);
        checkNotificationPermission();
        //Inicia el TimerService
        /*Intent serviceIntent=new Intent(this, TimerService.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            startForegroundService(serviceIntent);
        }else{
            startService(serviceIntent);
        }*/
        initRecyclearView();
        VerMasMostrar();
        BotonInicio();
        BotonPerfil();
        Button_formu();
        Button_lugar();
        Botton_mostrar();
        Reloj();
        boton_notificaciones();
        boton_donacione();
        setupDonacionesButton();
    }

    private void setupDonacionesButton() {
        donaciones_formun = findViewById(R.id.btn_donadores);
        updateDonacionesButtonState();
        donaciones_formun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTimerRunning()) {
                    startActivity(new Intent(Generl.this, Donacion.class));
                } else {
                    Toast.makeText(Generl.this, "El período de donación está en curso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isTimerRunning() {
        SharedPreferences prefs = getSharedPreferences("TimerPrefs", MODE_PRIVATE);
        long startTime = prefs.getLong("StartTime", 0);
        long currentTime = prefs.getLong("StartTime", 0);
        return startTime != 0 && (System.currentTimeMillis() - currentTime) < (30 * 24 * 60 * 60 * 1000L);//30 dias en milisegundos
    }
  /* private void startTime(){
        long startTime = System.currentTimeMillis();
        prefs.edit().putLong("StartTime", startTime).apply();

        Intent serviceIntent = new Intent(this, TimerService.class);
        serviceIntent.putExtra("StartTime", startTime);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }

        updateDonacionesButtonState();
    }*/
    private void updateDonacionesButtonState(){
        if(isTimerRunning()){
            donaciones_formun.setEnabled(false);
            donaciones_formun.setAlpha(0.5f);
        }else{
            donaciones_formun.setEnabled(true);
            donaciones_formun.setAlpha(1.0f);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        updateDonacionesButtonState();
    }
    private void checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, PERMISSION_REQUEST_CODE);
            }
        }
    }
    /*private void startTimerService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 y superior
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, PERMISSION_REQUEST_CODE);
            } else {
                startService();
            }
        } else {
            // Para versiones anteriores a Android 13, no necesitamos solicitar el permiso POST_NOTIFICATIONS
            startService();
        }
    }*/
   /* private void startService() {
        Intent serviceIntent = new Intent(this, TimerService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }
    }*/
   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode==PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startTimerService();
            }else{
                //manejar permiso denegado
            }
        }
    }*/
    /*private void donaciones_formun() {
        TextView donaciones_formun=findViewById(R.id.btn_donadores);
        donaciones_formun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, Donacion.class));
            }
        });
    }*/
    private void boton_donacione() {
        LinearLayout formulariosVer=findViewById(R.id.campanabtn);
        formulariosVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, CampanaMostrar.class));
            }
        });
    }
    private void boton_notificaciones() {
        ImageView boton_notificaciones = findViewById(R.id.button_notificaciones);
        boton_notificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotificationDialog();
            }
        });
    }
    private void showNotificationDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notificaciones");
        builder.setMessage("¿Desea activar o desactivar las notificaciones?");
        builder.setPositiveButton("Activar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enableNotifications();
            }
        });
        builder.setNegativeButton("Desactivar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                disableNotifications();
            }
        });
        builder.show();
    }
    private void enableNotifications() {
        // Aquí puedes añadir la lógica para activar las notificaciones
        // Por ejemplo, guardar una preferencia
        SharedPreferences prefs = getSharedPreferences("TimerPrefs", MODE_PRIVATE);
        prefs.edit().putBoolean("notifications_enabled", true).apply();
        Toast.makeText(this, "Notificaciones activadas", Toast.LENGTH_SHORT).show();
    }

    private void disableNotifications() {
        // Aquí puedes añadir la lógica para desactivar las notificaciones
        // Por ejemplo, guardar una preferencia
        SharedPreferences prefs = getSharedPreferences("TimerPrefs", MODE_PRIVATE);
        prefs.edit().putBoolean("notifications_enabled", false).apply();
        Toast.makeText(this, "Notificaciones desactivadas", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(Generl.this, DonacionMostar.class));
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
                AlertDialog show = new AlertDialog.Builder(Generl.this)
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