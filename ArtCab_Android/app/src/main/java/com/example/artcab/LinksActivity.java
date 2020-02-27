package com.example.artcab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class LinksActivity extends AppCompatActivity {

    ArrayList<String> specials = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    ArrayList<String> tastes = new ArrayList<>();
    ArrayList<String> links = new ArrayList<>();
    String name;
    String email;
    String instagram;
    String phone;
    String whatsapp;
    String quote;
    String portfolio;
    EditText link1;
    EditText link2;
    EditText link3;
    Button goToProfilePicture;
    Button skipLinks;

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = new Bundle();
        specials = bundle.getStringArrayList("specials");
        genres = bundle.getStringArrayList("genres");
        tastes = bundle.getStringArrayList("tastes");
        name = bundle.getString("name");
        email = bundle.getString("email");
        instagram = bundle.getString("instagram");
        phone = bundle.getString("phone");
        whatsapp = bundle.getString("whatsapp");
        quote = bundle.getString("quote");
        portfolio = bundle.getString("portfolio");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
        link1 = findViewById(R.id.link_1);
        link2 =findViewById(R.id.link_2);
        link3 = findViewById(R.id.link_3);
        goToProfilePicture = findViewById(R.id.goto_password);
        skipLinks = findViewById(R.id.skip_links);

        goToProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (link1.getText().toString().length() == 0) {
                    link1.setError("Required");
                } else {
                    links.add(link1.getText().toString());
                    links.add(link2.getText().toString());
                    links.add(link3.getText().toString());
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(getApplicationContext(), LinksActivity.class);
                    bundle.putStringArrayList("specials", specials);
                    bundle.putStringArrayList("genres", genres);
                    bundle.putStringArrayList("tastes", tastes);
                    bundle.putString("name", name);
                    bundle.putString("email", email);
                    bundle.putString("phone", phone);
                    bundle.putString("instagram", instagram);
                    bundle.putString("whatsapp", whatsapp);
                    bundle.putString("quote", quote);
                    bundle.putString("portfolio", portfolio);
                    bundle.putStringArrayList("links", links);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        skipLinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getApplicationContext(), LinksActivity.class);
                bundle.putStringArrayList("specials", specials);
                bundle.putStringArrayList("genres", genres);
                bundle.putStringArrayList("tastes", tastes);
                bundle.putString("name", name);
                bundle.putString("email", email);
                bundle.putString("instagram", instagram);
                bundle.putString("phone", phone);
                bundle.putString("whatsapp", whatsapp);
                bundle.putString("quote", quote);
                bundle.putString("portfolio", portfolio);
                bundle.putString("links", null);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
