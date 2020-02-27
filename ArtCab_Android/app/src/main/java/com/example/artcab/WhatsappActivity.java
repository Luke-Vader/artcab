package com.example.artcab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class WhatsappActivity extends AppCompatActivity {

    ArrayList<String> specials = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    ArrayList<String> tastes = new ArrayList<>();
    String name;
    String email;
    String instagram;
    String phone;
    EditText whatsapp;
    Button goToQuote;
    Button skipWhatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);
        whatsapp = findViewById(R.id.whatsapp);
        skipWhatsapp = findViewById(R.id.skip_whatsapp);
        goToQuote = findViewById(R.id.goto_quote);

        Bundle bundle = getIntent().getExtras();
        specials = bundle.getStringArrayList("specials");
        genres = bundle.getStringArrayList("genres");
        tastes = bundle.getStringArrayList("tastes");
        name = bundle.getString("name");
        email = bundle.getString("email");
        instagram = bundle.getString("instagram");
        phone = bundle.getString("phone");

        goToQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whatsapp.getText().toString().length() == 0) {
                    whatsapp.setError("Required");
                } else {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(getApplicationContext(), QuoteActivity.class);
                    bundle.putStringArrayList("specials", specials);
                    bundle.putStringArrayList("genres", genres);
                    bundle.putStringArrayList("tastes", tastes);
                    bundle.putString("name", name);
                    bundle.putString("email", email);
                    bundle.putString("phone", phone);
                    bundle.putString("instagram", instagram);
                    bundle.putString("whatsapp", whatsapp.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        skipWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getApplicationContext(), QuoteActivity.class);
                bundle.putStringArrayList("specials", specials);
                bundle.putStringArrayList("genres", genres);
                bundle.putStringArrayList("tastes", tastes);
                bundle.putString("name", name);
                bundle.putString("email", email);
                bundle.putString("instagram", instagram);
                bundle.putString("phone", phone);
                bundle.putString("whatsapp", "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
