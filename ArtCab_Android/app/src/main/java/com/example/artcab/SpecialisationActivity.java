package com.example.artcab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SpecialisationActivity extends AppCompatActivity {

    TextView selectSpecials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialisation);
        selectSpecials = findViewById(R.id.special_select);

        selectSpecials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSpecials();
            }
        });
    }

    private void showSpecials() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Specialisations");

// Add a checkbox list
        String[] animals = {"horse", "cow", "camel", "sheep", "goat"};
        boolean[] checkedItems = {true, false, false, true, false};
        builder.setMultiChoiceItems(animals, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                // The user checked or unchecked a box
            }
        });

// Add OK and Cancel buttons
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // The user clicked OK
            }
        });
        builder.setNegativeButton("Cancel", null);

// Create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
