package com.example.termiar.Formulario;

import static com.example.termiar.R.id.historialbtn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termiar.Adapter.FormularioAdapter;
import com.example.termiar.Adapter.TrendsAdapter;
import com.example.termiar.Network.RetrofitFactory;
import com.example.termiar.R;
import com.example.termiar.Servicios.FormularioService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MostarFormularios  extends AppCompatActivity {
    private RecyclerView recyclerViewformularios;
    private FormularioAdapter formularioAdapter;

    //List<Formulario> formularioGet = new ArrayList<>();
    //List<Formulario> GetFormu = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostarformularios);

        //recyclerViewformularios = findViewById(R.id.recyclerViewformularios);
        //recyclerViewformularios.setLayoutManager(new LinearLayoutManager(this));

        mostarLocalData();
        /*
        Button btnMostar=findViewById(R.id.btnMostrar);
        btnMostar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mostarLocalData();
            }
        });
        */


    }

    private void mostarLocalData() {

        Retrofit retrofit = RetrofitFactory.build();
        FormularioService servicio1 = retrofit.create(FormularioService.class);
        Call<List<Formulario>> call1 = servicio1.GetFormulario();
        call1.enqueue(new Callback<List<Formulario>>() {
            @Override
            public void onResponse(Call<List<Formulario>> call, Response<List<Formulario>> response) {
                //Se conecto al MOCK
                if(!response.isSuccessful()){
                    Log.e("CONEXION","Error de la APP");
                }else {
                    Log.i("CONEXION","Conexion Correcta");
                    //Log.i("CONEXION",new Gson().toJson(response.body()));
                    Log.i("CONEXION","SI MUESTRA DATOS");

                    List<Formulario>GetFormu = response.body();

                    FormularioAdapter adapter = new FormularioAdapter(GetFormu);

                    RecyclerView rv = findViewById(R.id.recyclerViewformularios);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);

                    /*
                    formularioGet = response.body();

                   FormularioAdapter adapter = new FormularioAdapter(formularioGet);
                   RecyclerView rv = findViewById(R.id.recyclerViewformularios);
                   rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                   rv.setHasFixedSize(true);
                   rv.setAdapter(adapter);

                     */



                }
            }

            @Override
            public void onFailure(Call<List<Formulario>> call, Throwable t) {
                //NO hay conexion al MOCK
                Log.e("CONEXION","NO hay conexion al MOCK");
            }
        });


        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                FormularioDatabase db=FormularioDatabase.getDatabase(getApplicationContext());
                List<Formulario> formularios =db.formularioDao().getAllFormularios();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        formularioAdapter=new FormularioAdapter(formularios);
                        recyclerViewformularios.setAdapter(formularioAdapter);
                    }
                });
               for (Formulario formulario:formularios){
                   Log.d("MostrarFormularios", "ID: " + formulario.getId());
                   Log.d("MostrarFormularios", "Usuario: " + formulario.getUsuario());
                   Log.d("MostrarFormularios", "Talla: " + formulario.getTalla());
                   Log.d("MostrarFormularios", "Peso: " + formulario.getPeso());
                   Log.d("MostrarFormularios", "Fecha última donación: " + formulario.getFechaUltimaDonacion());
                   Log.d("MostrarFormularios", "Sexo: " + formulario.getSexo());
                   Log.d("MostrarFormularios", "Tipo de sangre: " + formulario.getTipoSangre());
                   Log.d("MostrarFormularios", "Impedimentos: " + formulario.isImpedimentos());
                   Log.d("MostrarFormularios", "Acepto términos: " + formulario.isAceptoTerminos());
                   Log.d("MostrarFormularios", "-----------------------------");
               }
            }
        }).start();

        */

    }
}
