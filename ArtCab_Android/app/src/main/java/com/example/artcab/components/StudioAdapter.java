package com.example.artcab.components;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudioAdapter extends RecyclerView.Adapter<StudioAdapter.ViewHolder> {

    private ArrayList<Studio> studios;
    private Context context;

    public StudioAdapter(ArrayList<Studio> studios, Context context) {
        this.studios = studios;
        this.context = context;
    }

    @NonNull
    @Override
    public StudioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StudioAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return studios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
