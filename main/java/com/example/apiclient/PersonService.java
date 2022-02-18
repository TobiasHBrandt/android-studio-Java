package com.example.apiclient;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PersonService {

    @Headers("Content-Type: application/json")
    @GET("Person")
    Call<List<Person>> getAllPerson();

    @GET("Person/{id}")
    Call<Person> getPersonById(@Path("id") int id);

    @POST("Person")
    Call<Void> addPerson(@Body Person p);

    @DELETE("Person/{id}")
    Call<Void> deletePersonById(@Path("id") int id);

    @PUT("Person")
    Call<Void> updatePerson(@Body Person p);
}
