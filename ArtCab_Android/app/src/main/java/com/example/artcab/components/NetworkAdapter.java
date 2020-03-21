package com.example.artcab.components;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artcab.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NetworkAdapter extends RecyclerView.Adapter<NetworkAdapter.ViewHolder> {

    StorageReference storageReference;
    FirebaseFirestore db;

    Dialog userDialog;
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
        db = FirebaseFirestore.getInstance();

        holder.name.setText(users.get(holder.getAdapterPosition()).getName());
        holder.quote.setText(users.get(holder.getAdapterPosition()).getQuote());

        storageReference.child("user/" + users.get(holder.getAdapterPosition()).getUserId()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerCrop().into(holder.image);
                    }
                });

        holder.userCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayUser(users.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout userCard;
        TextView name;
        TextView quote;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.network_username);
            quote = itemView.findViewById(R.id.network_quote);
            image = itemView.findViewById(R.id.network_profile_image);
            userCard = itemView.findViewById(R.id.user_container);

        }
    }

    private void displayUser(User user) {
        userDialog = new Dialog(context, android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
        userDialog.setContentView(R.layout.user_view);
        userDialog.show();

        ImageButton close = userDialog.findViewById(R.id.collapse);
        TextView username = userDialog.findViewById(R.id.profile_name);
        TextView quote = userDialog.findViewById(R.id.profile_quote);
        TextView portfolio = userDialog.findViewById(R.id.portfolio_text);
        final ImageView userImage = userDialog.findViewById(R.id.user_profile);
        RecyclerView specials = userDialog.findViewById(R.id.special_recycler);
        RecyclerView links = userDialog.findViewById(R.id.links_recycler);
        LinkAdapter linkAdapter;
        SpecialisationAdapter specialAdapter;

        linkAdapter = new LinkAdapter(user.getLinks(), context);
        specialAdapter = new SpecialisationAdapter(user.getSpecialisations(), context);
        links.setAdapter(linkAdapter);
        specials.setAdapter(specialAdapter);

        links.setLayoutManager(new LinearLayoutManager(context));
        specials.setLayoutManager(new LinearLayoutManager(context));

        storageReference.child("user/" + user.getUserId()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerCrop().into(userImage);
                    }
                });

        username.setText(user.getName());
        quote.setText(user.getQuote());
        portfolio.setText(user.getPortfolio());

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDialog.dismiss();
            }
        });

    }

}
