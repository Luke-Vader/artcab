package com.example.artcab.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
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
import com.example.artcab.components.Post;
import com.example.artcab.components.Studio;
import com.example.artcab.components.StudioAdapter;
import com.example.artcab.components.StudioImageAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
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
    private static final int PICK_IMG_REQUEST = 1;
    FirebaseFirestore db;
    FirebaseAuth auth;
    StorageReference storage;

    RecyclerView studioRecycler;
    RecyclerView imageRecycler;
    Dialog studioDialog;
    UUID uuid;
    ArrayList<Studio> studios;
    StudioAdapter studioAdapter;
    FloatingActionButton addStudio;
    ImageButton test;
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
    ArrayList<Uri> images;
    StudioImageAdapter imageAdapter;

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
        post = studioView.findViewById(R.id.post_studio);
        close = studioView.findViewById(R.id.collapse);
        addImages = studioView.findViewById(R.id.add_images);
        imageRecycler = studioView.findViewById(R.id.studio_image_container);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studioDialog.dismiss();
            }
        });

        addImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlacePicker();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    Studio studio = new Studio();
                    studio.setCarpetArea(carpetArea.getText().toString());
                    studio.setName(name.getText().toString());
                    studio.setRent(rent.getText().toString());
                    if (parking.isChecked()) {
                        studio.setParking("0");
                    } else {
                        studio.setParking("1");
                    }
                    if (equipment.isChecked()) {
                        studio.setEquipped("0");
                    } else {
                        studio.setEquipped("1");
                    }
                    studio.setDeposit(deposit.getText().toString());
                    studio.setDescription(description.getText().toString());
                    studio.setLocation(location.getText().toString());
                    studio.setPostedBy(auth.getCurrentUser().getUid());
                    studio.setUid(uuid.toString());
                    db.collection("studios").document(uuid.toString()).set(studio)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getActivity(), "Studio Posted", Toast.LENGTH_SHORT).show();
                                    addPost();
                                    studios.add(studio);
                                    studioAdapter.notifyDataSetChanged();
                                    studioDialog.dismiss();
                                }
                            });
                    for (int i = 0; i < images.size(); ++i) {
                        storage.child("studios").child(uuid.toString()).child(Integer.toString(i + 1)).putFile(images.get(i));
                    }

                } else {
                    Toast.makeText(getActivity(), "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Studio Pictures"),PICK_IMG_REQUEST);
    }

    private void setAdapter() {
        studioAdapter = new StudioAdapter(studios, getActivity());
        studioRecycler.setAdapter(studioAdapter);
        studioAdapter.notifyDataSetChanged();
    }

    private boolean validate() {
        if (name.getText().toString().isEmpty() || description.getText().toString().isEmpty() || carpetArea.getText().toString().isEmpty() || rent.getText().toString().isEmpty() || deposit.getText().toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private void showPlacePicker() {
        PingPlacePicker.IntentBuilder builder = new PingPlacePicker.IntentBuilder();
        builder.setAndroidApiKey("AIzaSyAv4dlLRkzDZScczNsC0E2XLu_-IBo8VA4")
                .setMapsApiKey("AIzaSyAv4dlLRkzDZScczNsC0E2XLu_-IBo8VA4");

        try {
            Intent placeIntent = builder.build(getActivity());
            startActivityForResult(placeIntent, REQUEST_PLACE_PICKER);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQUEST_PLACE_PICKER) && (resultCode == RESULT_OK)) {
            Place place = PingPlacePicker.getPlace(data);
            if (place != null) {
                StringBuilder coordinates = new StringBuilder();
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                coordinates.append("geo:0,0?q=");
                coordinates.append(latitude);
                coordinates.append(",");
                coordinates.append(longitude);
                coordinates.append("(Studio)");
                location.setText(coordinates);
            }
        }

        images = new ArrayList<>();
        if (requestCode == PICK_IMG_REQUEST && resultCode == RESULT_OK) {
            addImages.setVisibility(View.GONE);
            if (data.getClipData() != null) {
                for (int i = 0; i < data.getClipData().getItemCount(); ++i) {
                    images.add(data.getClipData().getItemAt(i).getUri());
                }
            } else if (data.getData() != null) {
                images.add(data.getData());
            }
            imageRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            setUploadAdapter();
        }

    }

    private void setUploadAdapter() {
        imageAdapter = new StudioImageAdapter(getActivity(), images);
        imageRecycler.setAdapter(imageAdapter);
    }

    private void addPost() {
        Post post = new Post();
        post.setUid(uuid.toString());
        post.setTitle(name.getText().toString());
        post.setObject(db.collection("studios").document(uuid.toString()));
        post.setType("Studio");
        db.collection("users").document(auth.getCurrentUser().getUid()).collection("posts").document(uuid.toString()).set(post);

    }

}
