package com.example.artcab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class PasswordActivity extends AppCompatActivity {

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
    EditText password;
    EditText confirmPassword;
    Button goToProfilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        goToProfilePicture = findViewById(R.id.goto_profile_picture);

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
        portfolio = bundle.getString("portfolio");
        links = bundle.getStringArrayList("links");

        goToProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().length() != 0 && confirmPassword.getText().toString().length() != 0) {
                    if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(getApplicationContext(), ProfilePictureActivity.class);
                        bundle.putStringArrayList("specials", specials);
                        bundle.putStringArrayList("genres", genres);
                        bundle.putStringArrayList("tastes", tastes);
                        bundle.putStringArrayList("links", links);
                        bundle.putString("name", name);
                        bundle.putString("email", email);
                        bundle.putString("phone", phone);
                        bundle.putString("instagram", instagram);
                        bundle.putString("whatsapp", whatsapp);
                        bundle.putString("quote", quote);
                        bundle.putString("portfolio", portfolio);
                        bundle.putString("password", password.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        password.setError("Passwords Don't Match");
                        confirmPassword.setError("Passwords Don't Match");
                    }
                } else {
                    password.setError("Field can't be Empty");
                    confirmPassword.setError("Field can't be Empty");
                }
            }
        });

    }
}
