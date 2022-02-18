package com.example.apiclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    private static final String url = "http://10.0.2.2:8080/MyWebApi/api/";
    // emulator ip adresse 10.0.2.2

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory
            (GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType){
        return retrofit.create(serviceType);
    }
}
