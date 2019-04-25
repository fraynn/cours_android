package com.example.firstapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class EleveAdapter extends RecyclerView.Adapter<EleveAdapter.ViewHolder> {

    private List<EleveBean> eleveList;

    private OnEleveClickListener eleveClickListener;

    public EleveAdapter(List<EleveBean> eleveList) {
        this.eleveList = eleveList;
    }


    @NonNull
    @Override
    public EleveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_student, viewGroup, false);
        return new EleveAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EleveAdapter.ViewHolder viewHolder, int position) {
        EleveBean eleve = eleveList.get(position);

        viewHolder.tvEleveName.setText(eleve.getName());
        viewHolder.tvEleveFirstname.setText(eleve.getFirstName());

        Glide.with(viewHolder.tvEleveName.getContext()).load("https://i2.wp.com/www.letopdelhumour.fr/wp-content/uploads/2017/03/i282600889608361774._szw480h1280_.jpg?w=382").into(viewHolder.ivEleve);

        viewHolder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (eleveClickListener != null) {
                    eleveClickListener.onLongEleveClick(eleve, viewHolder.getAdapterPosition());
                }
                return true;
            }
        });
        viewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eleveClickListener != null) {
                    eleveClickListener.onEleveClick(eleve, viewHolder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eleveList.size();
    }

    public void setEleveClickListener(OnEleveClickListener eleveClickListener) {
        this.eleveClickListener = eleveClickListener;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEleveName, tvEleveFirstname;
        ImageView ivEleve;
        View root;

        public ViewHolder(View itemView) {
            super(itemView);
            tvEleveName = itemView.findViewById(R.id.tvEleveName);
            tvEleveFirstname = itemView.findViewById(R.id.tvEleveFirstname);
            ivEleve = itemView.findViewById(R.id.ivEleve);
            root = itemView.findViewById(R.id.root);
        }
    }

}

