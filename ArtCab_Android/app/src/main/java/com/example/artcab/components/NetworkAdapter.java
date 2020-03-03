package com.example.artcab.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artcab.R;

import java.util.ArrayList;

public class NetworkAdapter extends RecyclerView.Adapter<NetworkAdapter.ViewHolder> {

    ArrayList<User> users;
    Context context;

    public NetworkAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(users.get(holder.getAdapterPosition()).getName());
        holder.quote.setText(users.get(holder.getAdapterPosition()).getQuote());


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView quote;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.network_username);
            quote = itemView.findViewById(R.id.network_quote);
            image = itemView.findViewById(R.id.network_profile_image);
        }
    }
}
