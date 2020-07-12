package com.ilham.covid19.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ilham.covid19.R;
import com.ilham.covid19.databinding.CoronaListItemBinding;
import com.ilham.covid19.model.MainModel;

import java.util.ArrayList;
import java.util.List;

public class CoronaDataAdapter extends RecyclerView.Adapter<CoronaDataAdapter.CoronaViewHolder> {
    private ArrayList<MainModel> mainModels;

    public CoronaDataAdapter(List<MainModel> coronaList) {
    }

    @NonNull
    @Override
    public CoronaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CoronaListItemBinding coronaListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.corona_list_item, viewGroup, false);
        return new CoronaViewHolder(coronaListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CoronaViewHolder coronaViewHolder, int i) {
        MainModel c = mainModels.get(i);
        coronaViewHolder.coronaListItemBinding.setCorona(c);
    }

    @Override
    public int getItemCount() {
        if (mainModels != null) {
            return mainModels.size();
        } else {
            return 0;
        }
    }

    public void setCoronaList(ArrayList<MainModel> corona) {
        this.mainModels = corona;
        notifyDataSetChanged();
    }

    static class CoronaViewHolder extends RecyclerView.ViewHolder {
        private CoronaListItemBinding coronaListItemBinding;

        CoronaViewHolder(@NonNull CoronaListItemBinding coronaListItemBinding) {
            super(coronaListItemBinding.getRoot());
            this.coronaListItemBinding = coronaListItemBinding;
        }
    }
}