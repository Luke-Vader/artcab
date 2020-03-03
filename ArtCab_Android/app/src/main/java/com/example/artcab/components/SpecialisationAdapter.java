package com.example.artcab.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artcab.R;

import java.util.ArrayList;

public class SpecialisationAdapter extends RecyclerView.Adapter<SpecialisationAdapter.ViewHolder> {

    ArrayList<String> specialisations;
    Context context;

    public SpecialisationAdapter(ArrayList<String> specialisations, Context context) {
        this.specialisations = specialisations;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SpecialisationAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.specialisation_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.specialisation.setText(specialisations.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return specialisations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView specialisation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            specialisation = itemView.findViewById(R.id.special);
        }
    }

}
