package com.example.termiar.Formulario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.termiar.Activity.General.Generl;
import com.example.termiar.Activity.Main.MainActivity;
import com.example.termiar.Activity.Mapa.Map_donaciones;
import com.example.termiar.Activity.PerfilGeneral.Perfil;
import com.example.termiar.Activity.VerMasNoticias.VerMas;
import com.example.termiar.Network.RetrofitFactory;
import com.example.termiar.R;
import com.example.termiar.Servicios.FormularioService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Formulario_Registro extends AppCompatActivity {
    private EditText et_usuario,et_talla,et_peso,et_fecha_ultima_donacion;
    private Spinner spinner_sexo,spinner_tipo_sangre;
    private CheckBox cb_impedimentos,cb_acepto_terminos;
    private Button btn_enviar;
    private FormularioDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_registro);

        et_usuario=findViewById(R.id.et_usuario);
        et_talla=findViewById(R.id.et_talla);
        et_peso=findViewById(R.id.et_peso);
        et_fecha_ultima_donacion=findViewById(R.id.et_fecha_ultima_donacion);
        spinner_sexo=findViewById(R.id.spinner_sexo);
        spinner_tipo_sangre=findViewById(R.id.spinner_tipo_sangre);
        cb_impedimentos=findViewById(R.id.cb_impedimentos);
        cb_acepto_terminos=findViewById(R.id.cb_acepto_terminos);
        btn_enviar=findViewById(R.id.btn_enviar);

        //db=FormularioDatabase.getDatabase(getApplicationContext());

        setupSpinner(spinner_sexo, R.array.sexo_options);
        setupSpinner(spinner_tipo_sangre,R.array.tipo_sangre_options);
        // Verificar si los términos ya han sido aceptados
        SharedPreferences preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean terminosAceptados = preferences.getBoolean("terminos_aceptados", false);


        //BotonInicio();
        //BotonPerfil();
        //Button_formu();
        //Button_lugar();
        if (terminosAceptados) {
            // Si los términos ya fueron aceptados, redirigir directamente a la siguiente actividad
            startActivity(new Intent(Formulario_Registro.this, Generl.class));
            finish(); // Finalizar la actividad actual para que no se pueda regresar a ella
        }

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_acepto_terminos.isChecked()){

                    guardarFormulario();
                    startActivity(new Intent(Formulario_Registro.this, Generl.class));


                    guardarFormulario();
                    // Guardar en SharedPreferences que los términos han sido aceptados
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("terminos_aceptados", true);
                    editor.apply();
                    startActivity(new Intent(Formulario_Registro.this, Generl.class));
                    finish();

                }else{
                    Toast.makeText(Formulario_Registro.this,"Debe aceptar los terminos y condiciones",Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
    private void  setupSpinner(Spinner spinner,int arrayResource){
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,arrayResource, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void BotonInicio() {
        LinearLayout Inicio =findViewById(R.id.inicioBtn);
        Inicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Formulario_Registro.this, Generl.class));
            }
        });
    }

    private void Button_lugar(){
        LinearLayout lugarBtn=findViewById(R.id.lugarBtn);
        lugarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Formulario_Registro.this, Map_donaciones.class));
            }
        });
    }

    private void Button_formu(){
        LinearLayout configuraicon=findViewById(R.id.configuraicon);
        configuraicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Formulario_Registro.this, Formulario_Registro.class));
            }
        });
    }
    private void BotonPerfil() {
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Formulario_Registro.this, Perfil.class));
            }
        });
    }

    private void guardarFormulario(){


        Retrofit retrofit23 = RetrofitFactory.build();

        FormularioService service = retrofit23.create(FormularioService.class);

        Formulario formulario=new Formulario();
        formulario.setUsuario(et_usuario.getText().toString());
        formulario.setTalla(Float.parseFloat(et_talla.getText().toString()));
        formulario.setPeso(Float.parseFloat(et_peso.getText().toString()));
        formulario.setSexo(spinner_sexo.getSelectedItem().toString());
        formulario.setFechaUltimaDonacion(et_fecha_ultima_donacion.getText().toString());
        formulario.setTipoSangre(spinner_tipo_sangre.getSelectedItem().toString());
        formulario.setImpedimentos(cb_impedimentos.isChecked());
        formulario.setAceptoTerminos(cb_acepto_terminos.isChecked());

        Call<Formulario> call = service.PostCrearFormulario(formulario);

        call.enqueue(new Callback<Formulario>() {
            @Override
            public void onResponse(Call<Formulario> call, Response<Formulario> response) {
                if (response.isSuccessful()){
                    Log.i("CONEXION", new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Formulario> call, Throwable t) {
                Log.e("CONEXION","No hay conexion");
            }
        });


        /*
        new Thread(new Runnable(){
            @Override
            public void run() {
                FormularioDatabase db=FormularioDatabase.getDatabase(getApplicationContext());
                db.formularioDao().insert(formulario);
                Log.d("Formulario_Registro","Formulario guardado localmente: "+formulario.toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Formulario_Registro.this,"Formulario guardado localmente",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();

         */
    }
    private  void mosformulariodb() {

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                FormularioDatabase db=FormularioDatabase.getDatabase(getApplicationContext());
                List<Formulario> formularios=db.formularioDao().getAllFormularios();
                for (Formulario formulario:formularios){
                    Log.d("Formulario_Registro","Formulario recuperado de la base de datos: "+formulario.getUsuario());
                }
            }
        }).start();

         */
    }
}
