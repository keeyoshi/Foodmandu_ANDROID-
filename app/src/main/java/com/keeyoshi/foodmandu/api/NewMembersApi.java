package com.keeyoshi.foodmandu.api;

import com.keeyoshi.foodmandu.model.Member;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface NewMembersApi {
    @GET("members")
    Call<List<Member>> getNewMembers();

    @Multipart
    @POST("upload")
    Call<Member> uploadImage(@Part MultipartBody.Part img);

    @GET("members")
    Call<Member> getImage(@Header("Authorization") String id);
}
