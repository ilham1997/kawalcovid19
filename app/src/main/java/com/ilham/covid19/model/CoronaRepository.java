package com.ilham.covid19.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ilham.covid19.network.ApiEndpoint;
import com.ilham.covid19.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoronaRepository {
    private String TAG = "CoronaRepository";
    private MutableLiveData<List<MainModel>> mutableLiveData = new MutableLiveData<>();
    public CoronaRepository() {
    }
    public LiveData<List<MainModel>> getMutableLiveData() {
        final ApiEndpoint coronaDataService = ApiService.endpoint();
        Call<List<MainModel>> call = coronaDataService.getCorona();
        call.enqueue(new Callback<List<MainModel>>() {
            @Override
            public void onResponse(Call<List<MainModel>> call, Response<List<MainModel>> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());;
                }
            }
            @Override
            public void onFailure(Call<List<MainModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getCause());
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                Log.e(TAG, "onFailure: " + t.getMessage());
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
        return mutableLiveData;
    }
}
