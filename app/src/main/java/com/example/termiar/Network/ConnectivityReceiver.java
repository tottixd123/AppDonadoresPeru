package com.example.termiar.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.termiar.Formulario.Formulario;
import com.example.termiar.Formulario.FormularioApi;
import com.example.termiar.Formulario.FormularioDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
public class ConnectivityReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo  activeNetwork  = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        Log.d("TAG", "Connectivcity changed: "+isConnected);
        if (isConnected){
            Toast.makeText(context,"conectar a internet",Toast.LENGTH_SHORT).show();
            enviarFormulariosPendientes(context);
        }
    }
    private void enviarFormulariosPendientes(Context context){
        FormularioDatabase db = FormularioDatabase.getDatabase(context);
        List<Formulario> formularios = db.formularioDao().getAllFormularios();

        FormularioApi formularioservice = RetrofitClient.getRetrofitInstance().create(FormularioApi.class);
        Log.d("TAG", "enviarFormulariosPendientes: "+formularios.size());
        for (Formulario formulario:formularios){
            Call<Void> call= formularioservice.enviarFormulario(formulario);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()){
                        Log.d("TAG", "Formulario enviado exitosamente");
                        Toast.makeText(context,"Formulario enviado",Toast.LENGTH_SHORT).show();
                    }else{
                        Log.d("TAG", "Error al enviar formulario"+formulario);
                        Toast.makeText(context,"Error al enviar formulario",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("TAG", "Error al enviar formulario"+formulario,t);
                    Toast.makeText(context,"Error al enviar formulario",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    }


*/