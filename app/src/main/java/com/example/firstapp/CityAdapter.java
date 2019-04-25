package com.example.firstapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<CityBean> cityList;

    public CityAdapter(List<CityBean> cityList) {
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_city, viewGroup, false);
        return new CityAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder viewHolder, int position) {
        CityBean city = cityList.get(position);

        viewHolder.tvCodePostal.setText(String.valueOf(city.getCp()));
        viewHolder.tvCityName.setText(city.getVille());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodePostal, tvCityName;
        View rootCodePostal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCityName = itemView.findViewById(R.id.tvCityName);
            tvCodePostal = itemView.findViewById(R.id.tvCodePostal);
            rootCodePostal = itemView.findViewById(R.id.rootCodePostal);
        }
    }
}
