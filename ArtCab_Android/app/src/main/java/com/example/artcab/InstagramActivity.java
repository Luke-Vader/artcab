package com.example.artcab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class InstagramActivity extends AppCompatActivity {

    ArrayList<String> specials = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    ArrayList<String> tastes = new ArrayList<>();
    String name;
    String email;
    EditText instagram;
    Button goToPhone;

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = new Bundle();
        specials = bundle.getStringArrayList("specials");
        genres = bundle.getStringArrayList("genres");
        tastes = bundle.getStringArrayList("tastes");
        name = bundle.getString("name");
        email = bundle.getString("email");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);
        instagram = findViewById(R.id.instagram);
        goToPhone = findViewById(R.id.goto_phone);

        goToPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });



    }
}
