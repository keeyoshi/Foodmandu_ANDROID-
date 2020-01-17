package com.keeyoshi.foodmandu.api;

import com.keeyoshi.foodmandu.model.Detail;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Super {
    @GET("super7")
    Call<List<Detail>> getSuper7();

    @Multipart
    @POST("upload")
    Call<Detail> uploadImage(@Part MultipartBody.Part img);

    @GET("super7")
    Call<Detail> getImage(@Header("Authorization") String id);
}
