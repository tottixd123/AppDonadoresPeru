package com.example.termiar.Servicios;

import com.example.termiar.Formulario.Formulario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FormularioService {

    @GET("formularios")
    Call<List<Formulario>> GetFormulario();

    @POST("formularios")
    Call<Formulario> PostCrearFormulario (@Body Formulario formularioPost);

    @DELETE
    Call<Formulario>  delete(Formulario formulario);



}
