package com.example.artcab.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.artcab.R;

import java.util.ArrayList;

public class GenreActivity extends AppCompatActivity {

    TextView selectGenres;
    ArrayList<String> specials = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    String selected;
    Button goToTaste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        selectGenres = findViewById(R.id.genre_select);
        goToTaste = findViewById(R.id.goto_taste);

        Bundle bundle = getIntent().getExtras();
        specials = bundle.getStringArrayList("specials");

        selectGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGenres();
            }
        });

        goToTaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("specials", specials);
                bundle.putStringArrayList("genres", genres);
                Intent intent = new Intent(getApplicationContext(), TasteActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void showGenres() {
        selected = "";
        genres.clear();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Genres");
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
                selectGenres.setText(selected);
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
