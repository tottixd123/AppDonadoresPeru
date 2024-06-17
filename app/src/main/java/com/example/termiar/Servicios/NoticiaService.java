package com.example.termiar.Servicios;

import com.example.termiar.Domain.TrendSDomain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NoticiaService {

    @GET("noticias")
    Call<List<TrendSDomain>> GetNoticias();

    @GET("noticias/{id}")
    Call<TrendSDomain> idNoticia(@Path("id")int id);

}
