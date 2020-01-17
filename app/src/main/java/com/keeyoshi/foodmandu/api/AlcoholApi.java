package com.keeyoshi.foodmandu.api;

import com.keeyoshi.foodmandu.model.Alcohol;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlcoholApi {

    @GET("alcohol")
    Call<List<Alcohol>> getAlcohol();

}
