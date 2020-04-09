package com.example.artcab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artcab.components.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class EditUserActivity extends AppCompatActivity {

    final static int PICK_IMG_REQUEST = 71;
    private Uri filepath;

    FirebaseFirestore db;
    StorageReference storage;
    FirebaseAuth auth;

    ArrayList<String> specials = new ArrayList<>();
    ArrayList<String> links = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    ArrayList<String> tastes = new ArrayList<>();

    ImageView editSpecials;
    ImageView editLinks;
    ImageView editTastes;
    ImageView editGenres;
    ImageView profileImage;

    EditText name;
    EditText quote;
    EditText email;
    EditText portfolio;
    EditText instagram;
    EditText phone;
    EditText whatsApp;
    TextView special;
    TextView genre;
    TextView link;
    TextView taste;
    User serverUser;
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance().getReference();

        name = findViewById(R.id.name);
        quote = findViewById(R.id.quote);
        email = findViewById(R.id.email);
        portfolio = findViewById(R.id.portfolio);
        instagram = findViewById(R.id.instagram);
        phone = findViewById(R.id.phone);
        whatsApp = findViewById(R.id.whatsapp);
        special = findViewById(R.id.specials);
        genre = findViewById(R.id.genres);
        link = findViewById(R.id.links);
        taste = findViewById(R.id.tastes);
        editGenres = findViewById(R.id.edit_genres);
        editLinks = findViewById(R.id.edit_links);
        editSpecials = findViewById(R.id.edit_specials);
        editTastes = findViewById(R.id.edit_tastes);
        profileImage = findViewById(R.id.profile_image);

        storage.child("users").child(auth.getCurrentUser().getUid()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImage);
                    }
                });

        db.collection("users").document(auth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        serverUser = documentSnapshot.toObject(User.class);
                        name.setText(serverUser.getName());
                        quote.setText(serverUser.getQuote());
                        email.setText(serverUser.getEmail());
                        portfolio.setText(serverUser.getPortfolio());
                        instagram.setText(serverUser.getInstagram());
                        phone.setText(serverUser.getPhone());
                        whatsApp.setText(serverUser.getWhatsapp());
                        link.setText(serverUser.getLinks().toString().substring(1,serverUser.getLinks().toString().length() - 1));
                        special.setText(serverUser.getSpecialisations().toString().substring(1,serverUser.getSpecialisations().toString().length() - 1));
                        genre.setText(serverUser.getGenres().toString().substring(1,serverUser.getGenres().toString().length() - 1));
                        taste.setText(serverUser.getTastes().toString().substring(1,serverUser.getTastes().toString().length() - 1));

                    }
                });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProfileImage();
            }
        });

        editSpecials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSpecials();
            }
        });

        editGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGenres();
            }
        });

        editTastes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTastes();
            }
        });

    }

    private void showSpecials() {
        selected = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Specialisations");
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

        builder.setMultiChoiceItems(specialisations, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    specials.add(specialisations[which]);
                } else if (specials.contains(specialisations[which])) {
                    specials.remove(specialisations[which]);
                }
            }
        });

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (String special : specials) {
                    selected += special + ", ";
                }
                special.setText(selected);
                Toast.makeText(EditUserActivity.this, specials.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showGenres() {
        selected = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Specialisations");
        final String[] preferences = {
                "Comedy",
                "Drama",
                "Action",
                "Tragedy",
                "Thriller",
                "Horror",
                "Noir",
                "Experimental",
                "Romance",
                "Adventure",
                "Documentary",
                "Animation",
                "Silent"
        };

        builder.setMultiChoiceItems(preferences, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    genres.add(preferences[which]);
                } else if (genres.contains(preferences[which])) {
                    genres.remove(preferences[which]);
                }
            }
        });

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (String genre : genres) {
                    selected += genre + " ";
                }
                genre.setText(selected);
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showTastes() {
        selected = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Specialisations");
        final String[] preferences = {
                "Bollywood",
                "Hollywood",
                "European",
                "Japanese",
                "Korean",
                "Others"
        };

        builder.setMultiChoiceItems(preferences, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    tastes.add(preferences[which]);
                } else if (tastes.contains(preferences[which])) {
                    tastes.remove(preferences[which]);
                }
            }
        });

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (String taste : tastes) {
                    selected += taste + " ";
                }
                taste.setText(selected);
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void changeProfileImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select New Profile Picture"), PICK_IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMG_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                profileImage.setImageBitmap(bitmap);
            }
            catch (IOException e) {

            }
        }
    }

}
