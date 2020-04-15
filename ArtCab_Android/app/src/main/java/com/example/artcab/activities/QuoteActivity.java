package com.example.artcab.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.artcab.R;

import java.util.ArrayList;

public class QuoteActivity extends AppCompatActivity {

    ArrayList<String> specials = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    ArrayList<String> tastes = new ArrayList<>();
    String name;
    String email;
    String instagram;
    String phone;
    String whatsapp;
    EditText quote;
    Button goToPortFolio;
    Button skipQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        quote = findViewById(R.id.quote);
        goToPortFolio = findViewById(R.id.goto_portfolio);
        skipQuote = findViewById(R.id.skip_quote);

        Bundle bundle = getIntent().getExtras();
        specials = bundle.getStringArrayList("specials");
        genres = bundle.getStringArrayList("genres");
        tastes = bundle.getStringArrayList("tastes");
        name = bundle.getString("name");
        email = bundle.getString("email");
        instagram = bundle.getString("instagram");
        phone = bundle.getString("phone");
        whatsapp = bundle.getString("whatsapp");

        goToPortFolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quote.getText().toString().length() == 0) {
                    quote.setError("Required");
                } else {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(getApplicationContext(), PortfolioActivity.class);
                    bundle.putStringArrayList("specials", specials);
                    bundle.putStringArrayList("genres", genres);
                    bundle.putStringArrayList("tastes", tastes);
                    bundle.putString("name", name);
                    bundle.putString("email", email);
                    bundle.putString("phone", phone);
                    bundle.putString("instagram", instagram);
                    bundle.putString("whatsapp", whatsapp);
                    bundle.putString("quote", quote.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        skipQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getApplicationContext(), PortfolioActivity.class);
                bundle.putStringArrayList("specials", specials);
                bundle.putStringArrayList("genres", genres);
                bundle.putStringArrayList("tastes", tastes);
                bundle.putString("name", name);
                bundle.putString("email", email);
                bundle.putString("instagram", instagram);
                bundle.putString("phone", phone);
                bundle.putString("whatsapp", whatsapp);
                bundle.putString("quote", "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
