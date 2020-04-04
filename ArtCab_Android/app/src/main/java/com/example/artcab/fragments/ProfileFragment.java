package com.example.artcab.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artcab.LoginOrSignupActivity;
import com.example.artcab.MainActivity;
import com.example.artcab.R;
import com.example.artcab.components.LinkAdapter;
import com.example.artcab.components.SpecialisationAdapter;
import com.example.artcab.components.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.opencensus.trace.export.SpanData;

public class ProfileFragment extends Fragment {

    Button login;
    Button logout;
    private ImageView profilePicture;

    User user;
    TextView name;
    TextView quote;
    TextView portfolio;
    RecyclerView specialisations;
    RecyclerView links;
    ScrollView signedIn;
    RelativeLayout signIn;
    LinkAdapter linkAdapter;
    SpecialisationAdapter specialAdapter;

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseStorage storage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signIn = getActivity().findViewById(R.id.login_interface);
        signedIn = getActivity().findViewById(R.id.profile_interface);
        profilePicture = getActivity().findViewById(R.id.user_profile);
        name = getActivity().findViewById(R.id.profile_name);
        quote = getActivity().findViewById(R.id.profile_quote);
        portfolio = getActivity().findViewById(R.id.portfolio_text);
        specialisations = getActivity().findViewById(R.id.special_recycler);
        links = getActivity().findViewById(R.id.links_recycler);
        login = getActivity().findViewById(R.id.login_button);
        logout = getActivity().findViewById(R.id.logout);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        if (auth.getCurrentUser() == null) {
            signedIn.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
            signIn.setVisibility(View.VISIBLE);
            login();
        } else {
            signedIn.setVisibility(View.VISIBLE);
            signIn.setVisibility(View.GONE);
            getData();
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

    }


    private void login() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoginOrSignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        storage.getReference().child("user/" + auth.getCurrentUser().getUid()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerCrop().into(profilePicture);
                    }
                });

        db.collection("users").document(auth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        user = documentSnapshot.toObject(User.class);
                        name.setText(user.getName());
                        quote.setText(user.getQuote());
                        portfolio.setText(user.getPortfolio());
                        setAdapter();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "No User Found", Toast.LENGTH_SHORT).show();
                    }
                });

        links.setLayoutManager(new LinearLayoutManager(getActivity()));
        specialisations.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setAdapter() {
        linkAdapter = new LinkAdapter(user.getLinks(), getActivity());
        specialAdapter = new SpecialisationAdapter(user.getSpecialisations(), getActivity());
        links.setAdapter(linkAdapter);
        specialisations.setAdapter(specialAdapter);
    }

}
