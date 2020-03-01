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

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.ViewHolder> {

    ArrayList<String> links;
    Context context;

    public LinkAdapter(ArrayList<String> links, Context context) {
        this.links = links;
        this.context = context;
    }

    @NonNull
    @Override
    public LinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinkAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.link_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinkAdapter.ViewHolder holder, int position) {
        holder.link.setText(links.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView link;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            link = itemView.findViewById(R.id.link);
        }
    }

}
