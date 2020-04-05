package com.example.artcab.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artcab.R;
import com.example.artcab.components.IdeaAdapter;
import com.example.artcab.components.NetworkAdapter;
import com.example.artcab.components.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NetworksFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseFirestore db;
    RecyclerView recyclerView;
    NetworkAdapter adapter;
    ImageButton sort;
    ArrayList<User> users;
    ArrayList<String> specials = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_network, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        recyclerView = getActivity().findViewById(R.id.network_container);
        sort = getActivity().findViewById(R.id.filter_networks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog();
                //adapter.getFilter().filter("cinematographer");
            }
        });
        
        users = new ArrayList<>();
        db.collection("users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User serverUser = document.toObject(User.class);
                                users.add(serverUser);
                            }
                            setAdapter();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "No Users", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setAdapter() {
        adapter = new NetworkAdapter(users, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void filterDialog() {
        AlertDialog.Builder specialsFilter = new AlertDialog.Builder(getActivity());
        specialsFilter.setTitle("Filter By Specialisation");
        final String[] specialisations = {
                "Director",
                "Writer",
                "Actor",
                "Cinematographer",
                "Editor",
                "Producer",
                "VFX",
                "Film Production",
                "Music and Sound",
                "Vlogger"
        };

        specialsFilter.setMultiChoiceItems(specialisations, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    specials.add(specialisations[which]);
                } else if (specials.contains(specialisations[which])) {
                    specials.remove(specialisations[which]);
                }
            }
        });

        specialsFilter.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selected = null;
                for (String special : specials) {
                    selected += special + " ";
                }
                adapter.getFilter().filter(selected);
            }
        });
        specialsFilter.setNegativeButton("Cancel", null);
        AlertDialog dialog = specialsFilter.create();
        dialog.show();


    }
}
