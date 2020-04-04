package com.example.artcab.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
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
import com.google.android.libraries.places.api.model.Place;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rtchagas.pingplacepicker.PingPlacePicker;

import java.util.ArrayList;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class StudioFragment extends Fragment {

    int REQUEST_PLACE_PICKER = 71;
    FirebaseFirestore db;
    FirebaseAuth auth;
    StorageReference storage;

    RecyclerView studioRecycler;
    Dialog studioDialog;
    UUID uuid;
    ArrayList<Studio> studios;
    StudioAdapter studioAdapter;
    FloatingActionButton addStudio;
    //ImageButton test;
    EditText name;
    EditText carpetArea;
    EditText rent;
    Switch parking;
    Switch equipment;
    EditText deposit;
    EditText description;
    TextView location;
    Button addImages;
    Button post;
    ImageButton close;

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
        /*if (!Places.isInitialized()) {
            Places.initialize(getActivity(), "AIzaSyA1Ij65-z4eFi6eITqDcU5TYE7Q-u6S510");
        }*/

        addStudio = view.findViewById(R.id.add_studio);
        studioRecycler = view.findViewById(R.id.studio_container);
        //test = view.findViewById(R.id.filter_studios);

        /*test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creates an Intent that will load a map of San Francisco
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=34.99,-106.61(Treasure)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });*/

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
        uuid = UUID.randomUUID();

        View studioView = getLayoutInflater().inflate(R.layout.studio_dialog, null);
        studioDialog = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
        studioDialog.setContentView(studioView);
        studioDialog.show();

        name = studioView.findViewById(R.id.studio_name);
        carpetArea = studioView.findViewById(R.id.studio_area);
        rent = studioView.findViewById(R.id.studio_rent);
        parking = studioView.findViewById(R.id.studio_parking);
        equipment = studioView.findViewById(R.id.studio_equipment);
        deposit = studioView.findViewById(R.id.studio_deposit);
        description = studioView.findViewById(R.id.studio_description);
        location = studioView.findViewById(R.id.studio_location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlacePicker();
            }
        });

    }

    private void setAdapter() {
        studioAdapter = new StudioAdapter(studios, getActivity());
        studioRecycler.setAdapter(studioAdapter);
        studioAdapter.notifyDataSetChanged();
    }

    private void showPlacePicker() {
        PingPlacePicker.IntentBuilder builder = new PingPlacePicker.IntentBuilder();
        builder.setAndroidApiKey("AIzaSyAv4dlLRkzDZScczNsC0E2XLu_-IBo8VA4")
                .setMapsApiKey("AIzaSyAv4dlLRkzDZScczNsC0E2XLu_-IBo8VA4");

        // If you want to set a initial location rather then the current device location.
        // NOTE: enable_nearby_search MUST be true.
        // builder.setLatLng(new LatLng(37.4219999, -122.0862462))

        try {
            Intent placeIntent = builder.build(getActivity());
            startActivityForResult(placeIntent, REQUEST_PLACE_PICKER);
        }
        catch (Exception ex) {
            Toast.makeText(getActivity(), "WELL SHIT", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
            // Google Play services is not available...
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQUEST_PLACE_PICKER) && (resultCode == RESULT_OK)) {
            Place place = PingPlacePicker.getPlace(data);
            if (place != null) {
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                location.setText(latitude);
                Toast.makeText(getActivity(), latitude, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
