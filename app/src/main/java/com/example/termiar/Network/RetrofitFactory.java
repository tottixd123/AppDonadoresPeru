package com.example.termiar.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    public static Retrofit build (){
        return new Retrofit.Builder()
                .baseUrl("https://666a338e2e964a6dfed7e257.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit build2(){
        return new Retrofit .Builder()
                .baseUrl("https://666f8602f1e1da2be522febe.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
