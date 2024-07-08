package com.example.termiar.Servicios;

import com.example.termiar.Donacion.Donacion;
import com.example.termiar.Donacion.Donacion_Const;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DonadoresService {

    @GET("donaciones")
    Call<List<Donacion_Const>> GetDonacion();

    @POST("donaciones")
    Call<Donacion_Const> PostCrearDonacion(@Body Donacion_Const donacionesPost);

    @DELETE
    Call<Donacion_Const> DeleteDonacion(@Body Donacion donacion);

    @GET("donaciones")
    Call<List<Donacion_Const>> getRecords(@Query("limit") int limit, @Query("order") String order);



}
