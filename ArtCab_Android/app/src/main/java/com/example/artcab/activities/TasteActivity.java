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

public class TasteActivity extends AppCompatActivity {

    TextView selectTaste;
    ArrayList<String> specials = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    ArrayList<String> tastes = new ArrayList<>();
    String selected;
    Button goToName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taste);
        selectTaste = findViewById(R.id.taste_select);
        goToName = findViewById(R.id.goto_name);

        Bundle bundle = getIntent().getExtras();
        specials = bundle.getStringArrayList("specials");
        genres = bundle.getStringArrayList("genres");

        selectTaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTastes();
            }
        });

        goToName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("specials", specials);
                bundle.putStringArrayList("genres", genres);
                bundle.putStringArrayList("tastes", tastes);
                Intent intent = new Intent(getApplicationContext(), NameActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void showTastes() {
        selected = "";
        tastes.clear();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tastes");
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
                selectTaste.setText(selected);
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
