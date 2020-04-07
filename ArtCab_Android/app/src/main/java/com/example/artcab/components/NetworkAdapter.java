package com.example.artcab.components;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkAdapter extends RecyclerView.Adapter<NetworkAdapter.ViewHolder> implements Filterable {

    StorageReference storageReference;
    FirebaseFirestore db;

    Dialog userDialog;
    ArrayList<User> users;
    ArrayList<User> allUsers;
    Context context;

    public NetworkAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
        this.allUsers = new ArrayList<>(users);
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
        final User currentUser = users.get(position);

        holder.name.setText(currentUser.getName());
        holder.quote.setText(currentUser.getQuote());

        storageReference.child("user/" + currentUser.getUserId()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerCrop().into(holder.image);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        holder.image.setImageResource(R.drawable.user_dark_icon);
                    }
                });

        holder.userCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayUser(currentUser);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public Filter getFilter() {
        return specialisation;
    }

    private Filter specialisation = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<User> filtered = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtered.addAll(allUsers);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                String specials[] = filterPattern.split(" ");
                List<String> con = Arrays.asList(specials);
                for (String s : con) {
                    for (User user : allUsers) {
                        for (String special : user.getSpecialisations()) {
                            if (special.toLowerCase().contains(s)) {
                                if (!filtered.contains(user)) {
                                    filtered.add(user);
                                }
                            }
                        }
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            users.clear();
            users.addAll((ArrayList) results.values);
            notifyDataSetChanged();

        }
    };

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
