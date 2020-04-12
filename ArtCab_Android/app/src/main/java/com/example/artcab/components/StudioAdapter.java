package com.example.artcab.components;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudioAdapter extends RecyclerView.Adapter<StudioAdapter.ViewHolder> {

    FirebaseFirestore db;
    StorageReference storage;
    Dialog userDialog;

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
    public void onBindViewHolder(@NonNull final StudioAdapter.ViewHolder holder, final int position) {

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance().getReference();

        storage.child("studios/" + studios.get(position).getUid() + "/1").getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerCrop().into(holder.image);
                    }
                });

        holder.area.setText(studios.get(position).getCarpetArea() + " Sq.ft");
        holder.rent.setText("₹" + studios.get(position).getRent() + "/hour");
        holder.deposit.setText("Deposit:₹" + studios.get(position).getDeposit());
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
                try{
                    Uri gmmIntentUri = Uri.parse(studios.get(position).getLocation());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context. startActivity(mapIntent);
                } catch (Exception e){
                    Toast.makeText(context, "Location for " + studios.get(position).getName() + " not provided", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        holder.studio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("users").document(studios.get(position).getPostedBy()).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                User serverUser = documentSnapshot.toObject(User.class);
                                displayUser(serverUser);
                            }
                        });
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
        RelativeLayout studio;

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
            studio = itemView.findViewById(R.id.link);

        }
    }

    private void displayUser(final User user) {
        userDialog = new Dialog(context, android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
        userDialog.setContentView(R.layout.user_view);
        userDialog.show();

        ImageButton close = userDialog.findViewById(R.id.collapse);
        TextView username = userDialog.findViewById(R.id.profile_name);
        TextView quote = userDialog.findViewById(R.id.profile_quote);
        TextView portfolio = userDialog.findViewById(R.id.portfolio_text);
        ImageView whatsapp = userDialog.findViewById(R.id.whatsapp_connect);
        ImageView instagram = userDialog.findViewById(R.id.instagram_connect);
        ImageView email = userDialog.findViewById(R.id.email_connect);
        final ImageView userImage = userDialog.findViewById(R.id.user_profile);
        RecyclerView specials = userDialog.findViewById(R.id.special_recycler);
        RecyclerView links = userDialog.findViewById(R.id.links_recycler);
        LinkAdapter linkAdapter;
        SpecialisationAdapter specialAdapter;


        specialAdapter = new SpecialisationAdapter(user.getSpecialisations(), context);
        linkAdapter = new LinkAdapter(user.getLinks(), context);
        links.setAdapter(linkAdapter);
        specials.setAdapter(specialAdapter);

        links.setLayoutManager(new LinearLayoutManager(context));
        specials.setLayoutManager(new LinearLayoutManager(context));

        storage.child("user/" + user.getUserId()).getDownloadUrl()
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

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getWhatsapp().length() != 10) {
                    Toast.makeText(context, "No WhatsApp Number Provided", Toast.LENGTH_SHORT).show();
                } else {
                    Uri whatsappUri = Uri.parse("smsto:" + user.getWhatsapp());
                    Intent whatsapp = new Intent(Intent.ACTION_SENDTO, whatsappUri);
                    whatsapp.setPackage("com.whatsapp");
                    try {
                        context.startActivity(whatsapp);
                    } catch (Exception e) {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getInstagram().length() == 0) {
                    Toast.makeText(context, "No Instagram Profile Provided", Toast.LENGTH_SHORT).show();
                }else {
                    Uri instaUri = Uri.parse("http://instagram.com/_u/" + user.getInstagram());
                    Intent instagram = new Intent(Intent.ACTION_VIEW, instaUri);
                    instagram.setPackage("com.instagram.android");

                    try {
                        context.startActivity(instagram);
                    } catch (ActivityNotFoundException e) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://instagram.com/" + user.getInstagram())));
                    }
                }
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SENDTO);
                intent.setData(android.net.Uri.parse("mailto:" + user.getEmail()));
                Intent.createChooser(intent,"Email");
                context.startActivity(intent);
            }
        });

    }

}
