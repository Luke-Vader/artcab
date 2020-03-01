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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class IdeaAdapter extends RecyclerView.Adapter<IdeaAdapter.ViewHolder> {

    FirebaseFirestore db;
    FirebaseAuth auth;
    StorageReference storageReference;

    private ArrayList<Idea> ideas = new ArrayList<>();
    private Context context;

    public IdeaAdapter(Context context, ArrayList<Idea> ideas) {
        this.context = context;
        this.ideas = ideas;
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

        holder.quote.setText(ideas.get(position).getIdea());
        holder.user.setText(ideas.get(position).getUser());
        holder.time.setText(ideas.get(position).getTime());

        /*storageReference.child("users/" + ).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerCrop().into(holder.userImage);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Profile Photo Missing", Toast.LENGTH_SHORT).show();
                    }
                });*/

    }

    @Override
    public int getItemCount() {
        return ideas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView quote;
        TextView user;
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

}
