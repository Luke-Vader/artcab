package com.example.artcab;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artcab.components.Idea;
import com.example.artcab.components.IdeaAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.UUID;

public class IdeaFragment extends Fragment {

    FloatingActionButton addIdea;
    FirebaseAuth auth;
    FirebaseFirestore db;

    UUID uuid;
    ArrayList<Idea> ideas;
    String idea;
    EditText ideaText;
    String username;
    String userId;
    IdeaAdapter ideaAdapter;
    RecyclerView recyclerView;
    Dialog ideaDialog;
    ImageButton close;
    Button post;
    Spinner genreSelect;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ideas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        addIdea = getActivity().findViewById(R.id.add_idea);
        recyclerView = getActivity().findViewById(R.id.idea_container);

        ideas = new ArrayList<>();
        db.collection("ideas").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()){
                                Idea serverIdea = document.toObject(Idea.class);
                                ideas.add(serverIdea);
                            }
                            setAdapter();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "No Ideas", Toast.LENGTH_SHORT).show();
                    }
                });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (auth.getCurrentUser() == null) {
                    startActivity(new Intent(getActivity(), LoginOrSignupActivity.class));
                } else {
                    postIdea();
                }

            }
        });

        db.collection("ideas").document().get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Idea Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setAdapter() {
        ideaAdapter = new IdeaAdapter(getActivity(), ideas);
        recyclerView.setAdapter(ideaAdapter);
    }

    private void postIdea() {
        uuid = uuid.randomUUID();

        View ideaView = getLayoutInflater().inflate(R.layout.idea_dialog, null);
        ideaDialog = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
        ideaDialog.setContentView(ideaView);
        ideaDialog.show();

        post = ideaView.findViewById(R.id.post_idea);
        close = ideaView.findViewById(R.id.collapse);
        genreSelect = ideaView.findViewById(R.id.genre_spinner);
        ideaText = ideaView.findViewById(R.id.idea_text);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.genres, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSelect.setAdapter(adapter);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ideaDialog.dismiss();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    Idea idea = new Idea();
                    idea.setIdea(ideaText.getText().toString());
                    idea.setGenre(genreSelect.getSelectedItem().toString());
                    idea.setUser(auth.getCurrentUser().getUid());
                    db.collection("ideas").document(uuid.toString()).set(idea)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getActivity(), "Idea Posted", Toast.LENGTH_SHORT).show();
                                    ideaDialog.dismiss();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getActivity(),"", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    ideaText.setError("Required Field");
                }
            }
        });
    }

    private boolean validate() {
        if (ideaText.getText().toString().length() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
