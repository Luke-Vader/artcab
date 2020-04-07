package com.example.artcab.components;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artcab.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class StudioAdapter extends RecyclerView.Adapter<StudioAdapter.ViewHolder> {

    FirebaseFirestore db;
    StorageReference storage;

    private ArrayList<Studio> studios;
    private Context context;

    public StudioAdapter(ArrayList<Studio> studios, Context context) {
        this.studios = studios;
        this.context = context;
    }

    @NonNull
    @Override
    public StudioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudioAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.studio_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudioAdapter.ViewHolder holder, final int position) {

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance().getReference();

        holder.area.setText(studios.get(position).getCarpetArea() + " Sq.ft");
        holder.rent.setText(studios.get(position).getRent() + "/hour");
        holder.deposit.setText("Deposit: " + studios.get(position).getDeposit());
        holder.name.setText(studios.get(position).getName());
        holder.description.setText(studios.get(position).getDescription());
        if (studios.get(position).getParking().equals("1")) {
            holder.parking.setText("Unavailable");
        } else {
            holder.parking.setText("Available");
        }
        if (studios.get(position).getEquipped().equals("1")) {
            holder.equipment.setText("Unavailable");
        } else {
            holder.equipment.setText("Available");
        }
        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse(studios.get(position).getLocation());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context. startActivity(mapIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView description;
        TextView parking;
        TextView equipment;
        ImageView location;
        TextView rent;
        TextView area;
        TextView deposit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.studio_image);
            name = itemView.findViewById(R.id.studio_name);
            description = itemView.findViewById(R.id.studio_description);
            parking = itemView.findViewById(R.id.parking_value);
            equipment = itemView.findViewById(R.id.equipment_value);
            rent = itemView.findViewById(R.id.studio_rent);
            area = itemView.findViewById(R.id.studio_area);
            deposit = itemView.findViewById(R.id.studio_deposit);
            location = itemView.findViewById(R.id.studio_location);

        }
    }
}
