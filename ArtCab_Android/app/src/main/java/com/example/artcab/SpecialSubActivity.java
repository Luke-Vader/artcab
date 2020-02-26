package com.example.artcab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SpecialSubActivity extends AppCompatActivity {

    Button goToGenre;
    TextView selectSubSpecials;

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        ArrayList<String> specials = bundle.getStringArrayList("specials");
        if (specials.size() == 0) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_sub);
        goToGenre = findViewById(R.id.goto_genre);
        selectSubSpecials = findViewById(R.id.sub_special_select);

        selectSubSpecials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        goToGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
