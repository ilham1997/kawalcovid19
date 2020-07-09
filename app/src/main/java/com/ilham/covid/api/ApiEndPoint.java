package com.ilham.covid.api;

import com.ilham.covid.model.CountryModel;
import retrofit2.Call;
import retrofit2.http.GET;



public interface ApiEndPoint {
    @GET(Api.END_POINT_IDN)
    Call<CountryModel> getSummaryIdn();

}
