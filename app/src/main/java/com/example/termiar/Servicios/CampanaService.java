package com.example.termiar.Servicios;



import com.example.termiar.Campanas.Campana;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CampanaService {

    @GET("campanas")
    Call<List<Campana>> GetCampanas();


}
