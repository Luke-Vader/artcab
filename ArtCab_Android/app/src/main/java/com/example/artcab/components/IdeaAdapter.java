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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artcab.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class IdeaAdapter extends RecyclerView.Adapter<IdeaAdapter.ViewHolder> implements Filterable {

    FirebaseFirestore db;
    FirebaseAuth auth;
    User currentUser;
    StorageReference storageReference;
    Dialog userDialog;

    private ArrayList<Idea> ideas;
    private ArrayList<Idea> allIdeas;
    private Context context;

    public IdeaAdapter(Context context, ArrayList<Idea> ideas) {
        this.context = context;
        this.ideas = ideas;
        this.allIdeas = new ArrayList<>(ideas);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.idea_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        if ((new Date().getTime() - ideas.get(position).getTimestamp().getTime())/(1000*60) < 60 && (new Date().getTime() - ideas.get(position).getTimestamp().getTime())/(1000*60) >= 1) {
            holder.time.setText(Long.toString((new Date().getTime() - ideas.get(position).getTimestamp().getTime()) / (1000 * 60)) + "m");
        } else if ((new Date().getTime() - ideas.get(position).getTimestamp().getTime())/(1000*60) < 1) {
            holder.time.setText("Just Now");
        } else {
            holder.time.setText(Long.toString((new Date().getTime() - ideas.get(position).getTimestamp().getTime())/(1000*60*60)) + "h");
        }

        holder.quote.setText(ideas.get(holder.getAdapterPosition()).getIdea());

        db.collection("users").document(ideas.get(holder.getAdapterPosition()).getUser()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        currentUser = documentSnapshot.toObject(User.class);
                        holder.user.setText(documentSnapshot.getString("name"));
                    }
                });

        storageReference.child("user/" + ideas.get(holder.getAdapterPosition()).getUser()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerCrop().into(holder.userImage);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        holder.userImage.setImageResource(R.drawable.user_dark_icon);
                    }
                });

        holder.userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayUser(currentUser);
            }
        });

        holder.user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayUser(currentUser);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ideas.size();
    }

    @Override
    public Filter getFilter() {
        return genre;
    }

    private Filter genre = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
         ArrayList<Idea> filtered = new ArrayList<>();
         if (constraint == null || constraint.length() == 0) {
             filtered.addAll(allIdeas);
         } else {
             String filterPattern = constraint.toString().toLowerCase().trim();
             String genres[] = filterPattern.split(" ");
             List<String> con = Arrays.asList(genres);
             for (String s : con) {
                 for (Idea idea : allIdeas) {
                     if (idea.getGenre().toLowerCase().contains(s)) {
                         filtered.add(idea);
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
            ideas.clear();
            ideas.addAll((ArrayList) results.values);
            notifyDataSetChanged();

        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView quote;
        TextView user;
        TextView genre;
        TextView time;
        ImageView userImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quote = itemView.findViewById(R.id.idea_content);
            user = itemView.findViewById(R.id.username);
            time = itemView.findViewById(R.id.time_elapsed);
            userImage = itemView.findViewById(R.id.profile_image);

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
