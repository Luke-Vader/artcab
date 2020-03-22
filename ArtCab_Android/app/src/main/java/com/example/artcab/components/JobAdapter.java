package com.example.artcab.components;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    StorageReference storageReference;
    FirebaseFirestore db;

    Dialog userDialog;
    private ArrayList<Job> jobs;
    private Context context;

    public JobAdapter(ArrayList<Job> jobs, Context context) {
        this.jobs = jobs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.job_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        holder.description.setText(jobs.get(holder.getAdapterPosition()).getDescription());
        holder.title.setText(jobs.get(holder.getAdapterPosition()).getTitle());
        holder.location.setText(jobs.get(holder.getAdapterPosition()).getLocation());
        holder.organisation.setText(jobs.get(holder.getAdapterPosition()).getOrganisation());
        holder.adminName.setText(jobs.get(holder.getAdapterPosition()).getAdminName());

        holder.adminName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("users").document(jobs.get(holder.getAdapterPosition()).getPostedBy()).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                User user = documentSnapshot.toObject(User.class);
                                displayUser(user);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Unable to find user", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView organisation;
        TextView adminName;
        TextView location;
        TextView timeElapsed;
        String postedBy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.job_title);
            description = itemView.findViewById(R.id.job_description);
            timeElapsed = itemView.findViewById(R.id.time_elapsed);
            organisation = itemView.findViewById(R.id.job_organisation);
            location = itemView.findViewById(R.id.job_location);
            adminName = itemView.findViewById(R.id.posted_by);
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
