package com.ilham.covid19.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private CoronaRepository coronaRepository;
    public MainViewModel(@NonNull Application application) {
        super(application);
        coronaRepository = new CoronaRepository();
    }
    public LiveData<List<MainModel>> getAllCorona() {
        return coronaRepository.getMutableLiveData();
    }

}
