package com.example.artcab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IdeaAdapter extends RecyclerView.Adapter<IdeaAdapter.ViewHolder> {

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.quote.setText(ideas.get(position).getQuote());
        holder.user.setText(ideas.get(position).getUser());
        holder.time.setText(ideas.get(position).getTime());
        //Image has to be set

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

        }
    }

}
