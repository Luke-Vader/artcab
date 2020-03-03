package com.example.artcab.components;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artcab.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NetworkAdapter extends RecyclerView.Adapter<NetworkAdapter.ViewHolder> {

    StorageReference storageReference;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        storageReference = FirebaseStorage.getInstance().getReference();

        holder.name.setText(users.get(holder.getAdapterPosition()).getName());
        holder.quote.setText(users.get(holder.getAdapterPosition()).getQuote());

        storageReference.child("user/" + users.get(holder.getAdapterPosition()).getUserId()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerCrop().into(holder.image);
                    }
                });

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
