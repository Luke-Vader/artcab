package com.example.artcab.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artcab.LoginOrSignupActivity;
import com.example.artcab.R;
import com.example.artcab.components.Studio;
import com.example.artcab.components.StudioAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.UUID;

public class StudioFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;
    StorageReference storage;

    RecyclerView studioRecycler;
    Dialog studioDialog;
    UUID uuid;
    ArrayList<Studio> studios;
    StudioAdapter studioAdapter;
    FloatingActionButton addStudio;
    ImageButton test;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_studio, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance().getReference();

        addStudio = view.findViewById(R.id.add_studio);
        studioRecycler = view.findViewById(R.id.studio_container);
        test = view.findViewById(R.id.filter_studios);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creates an Intent that will load a map of San Francisco
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=34.99,-106.61(Treasure)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        studios = new ArrayList<>();
        db.collection("studios").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Studio serverStudio = documentSnapshot.toObject(Studio.class);
                                studios.add(serverStudio);
                            }
                            setAdapter();
                        }
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Toast.makeText(getActivity(), "No Studios", Toast.LENGTH_SHORT).show();
                    }
                });
        studioRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        addStudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (auth.getCurrentUser() == null) {
                    startActivity(new Intent(getActivity(), LoginOrSignupActivity.class));
                } else {
                    postStudio();
                }
            }
        });

    }

    private void postStudio() {
        uuid = uuid.randomUUID();

        View studioView = getLayoutInflater().inflate(R.layout.studio_dialog, null);
        studioDialog = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
        studioDialog.setContentView(studioView);
        studioDialog.show();



    }

    private void setAdapter() {
        studioAdapter = new StudioAdapter(studios, getActivity());
        studioRecycler.setAdapter(studioAdapter);
        studioAdapter.notifyDataSetChanged();
    }

}
