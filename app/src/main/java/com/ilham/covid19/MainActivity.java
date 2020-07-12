package com.ilham.covid19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;

import com.ilham.covid19.adapter.CoronaDataAdapter;
import com.ilham.covid19.databinding.ActivityMainBinding;
import com.ilham.covid19.model.MainModel;
import com.ilham.covid19.model.MainViewModel;
import com.ilham.covid19.network.ApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private TextView tvToday, positif, sembuh, meninggal;
    String hariIni;
    private ProgressDialog mProgressApp;
    private MainViewModel mainViewModel;
    SwipeRefreshLayout swipeRefresh;
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        initializationViews();

        mProgressApp = new ProgressDialog(this);
        mProgressApp.setTitle("Mohon Tunggu");
        mProgressApp.setCancelable(false);
        mProgressApp.setMessage("Sedang Menampilkan Data");
        mProgressApp.show();

        tvToday   = findViewById(R.id.tvDate);
        Date dateNow = Calendar.getInstance().getTime();
        hariIni   = (String) DateFormat.format("EEEE", dateNow);
        positif   = findViewById(R.id.positif);
        sembuh    = findViewById(R.id.sembuh);
        meninggal = findViewById(R.id.meninggal);
        //progressBar = findViewById(R.id.progressBar);

        getAllCorona();
        getDataFromApi();
        swipeRefresh.setOnRefreshListener(this::getAllCorona);
        getToday();
    }
    private void initializationViews(){
        swipeRefresh = findViewById(R.id.swipeRefresh);
        mRecyclerView = findViewById(R.id.viewCorona);
    }
    private void getAllCorona() {
        swipeRefresh.setRefreshing(true);
        mainViewModel.getAllCorona().observe(this, new Observer<List<MainModel>>() {
            @Override
            public void onChanged(@Nullable List<MainModel> coronaList) {
                swipeRefresh.setRefreshing(false);
                prepareRecyclerView(coronaList);
            }
        });
    }
    private void prepareRecyclerView(List<MainModel> coronaList) {
        CoronaDataAdapter coronaDataAdapter = new CoronaDataAdapter(coronaList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(coronaDataAdapter);
        coronaDataAdapter.setCoronaList((ArrayList<MainModel>) coronaList);
    }
    private void getToday(){
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d MMMM yyyy", date);
        String formatFix = hariIni + ", " + tanggal;
        tvToday.setText(formatFix);
    }
    private void getDataFromApi() {
        ApiService.endpoint().getData()
                .enqueue(new Callback<List<MainModel>>() {
                    @Override
                    public void onResponse(Call<List<MainModel>> call, Response<List<MainModel>> response) {
                        mProgressApp.dismiss();
                        if (response.isSuccessful()) {
                                List<MainModel> result = response.body();
                                showHasil(result);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<MainModel>> call, Throwable t) {
                        Log.d( TAG, t.toString());
                        mProgressApp.dismiss();
                    }
                });
    }
    private void showHasil(List<MainModel> mainModels){
        MainModel result = mainModels.get(0);
        positif.setText(result.getPositif());
        sembuh.setText(result.getSembuh());
        meninggal.setText(result.getMeninggal());
    }
}
