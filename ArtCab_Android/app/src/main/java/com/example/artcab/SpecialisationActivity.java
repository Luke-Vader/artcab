package com.example.artcab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class SpecialisationActivity extends AppCompatActivity {

    TextView selectSpecials;
    ArrayList<String> specials = new ArrayList<>();
    String selected;
    Button goToSpecialSubCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialisation);
        selectSpecials = findViewById(R.id.special_select);
        goToSpecialSubCat = findViewById(R.id.goto_spc_sub);

        selectSpecials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSpecials();
            }
        });

        goToSpecialSubCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("specials", specials);
                Intent intent = new Intent(getApplicationContext(), GenreActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
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
                    selected += special + " ";
                }
                selectSpecials.setText(selected);
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
