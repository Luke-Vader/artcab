package com.example.artcab.components;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.link_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final LinkAdapter.ViewHolder holder, int position) {
        int index = holder.getAdapterPosition()+1;
        String link = "#" + index + " " + links.get(holder.getAdapterPosition());
        holder.link.setText(link);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!links.get(holder.getAdapterPosition()).startsWith("http://")) {
                    String url = "http://" + links.get(holder.getAdapterPosition());
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(browserIntent);
                } else {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(links.get(holder.getAdapterPosition()))));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView link;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            link = itemView.findViewById(R.id.link);
            container = itemView.findViewById(R.id.link_box);
        }
    }
}
