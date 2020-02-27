package com.example.artcab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
    }
}
