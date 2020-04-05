package com.example.artcab.components;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artcab.R;

import java.util.ArrayList;

public class StudioImageAdapter extends RecyclerView.Adapter<StudioImageAdapter.ViewHolder> {

    Context context;
    ArrayList<Uri> images;

    public StudioImageAdapter(Context context, ArrayList<Uri> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public StudioImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudioImageAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.studio_image_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudioImageAdapter.ViewHolder holder, int position) {
        holder.image.setImageURI(images.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.studio_image);
        }
    }
}
