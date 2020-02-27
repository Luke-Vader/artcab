package com.example.artcab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class PortfolioActivity extends AppCompatActivity {

    ArrayList<String> specials = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    ArrayList<String> tastes = new ArrayList<>();
    String name;
    String email;
    String instagram;
    String phone;
    String whatsapp;
    String quote;
    EditText portfolio;
    Button goToLinks;
    Button skipPortFolio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        portfolio = findViewById(R.id.portfolio);
        goToLinks = findViewById(R.id.goto_links);
        skipPortFolio = findViewById(R.id.skip_portfolio);

        Bundle bundle = getIntent().getExtras();
        specials = bundle.getStringArrayList("specials");
        genres = bundle.getStringArrayList("genres");
        tastes = bundle.getStringArrayList("tastes");
        name = bundle.getString("name");
        email = bundle.getString("email");
        instagram = bundle.getString("instagram");
        phone = bundle.getString("phone");
        whatsapp = bundle.getString("whatsapp");
        quote = bundle.getString("quote");

        goToLinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (portfolio.getText().toString().length() == 0) {
                    portfolio.setError("Required");
                } else {
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
                    bundle.putString("portfolio", portfolio.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        skipPortFolio.setOnClickListener(new View.OnClickListener() {
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
                bundle.putString("portfolio", "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
