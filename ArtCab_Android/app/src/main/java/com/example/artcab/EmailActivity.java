package com.example.artcab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class EmailActivity extends AppCompatActivity {

    ArrayList<String> specials = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    ArrayList<String> tastes = new ArrayList<>();
    String name;
    EditText email;
    Button goToInsta;

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = new Bundle();
        specials = bundle.getStringArrayList("specials");
        genres = bundle.getStringArrayList("genres");
        tastes = bundle.getStringArrayList("tastes");
        name = bundle.getString("name");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        email = findViewById(R.id.email);
        goToInsta = findViewById(R.id.goto_insta);

        goToInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().length() == 0) {
                    email.setError("Required");
                } else {
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(getApplicationContext(), InstagramActivity.class);
                    bundle.putStringArrayList("specials", specials);
                    bundle.putStringArrayList("genres", genres);
                    bundle.putStringArrayList("tastes", tastes);
                    bundle.putString("name", name);
                    bundle.putString("email", email.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
}
