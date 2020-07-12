package com.ilham.covid19.network;

import com.ilham.covid19.model.MainModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("indonesia")
    Call<List<MainModel>> getData();
    @GET("indonesia/provinsi")
    Call<List<MainModel>> getCorona();
}
