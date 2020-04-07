package com.example.artcab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class ChangePasswordActivity extends AppCompatActivity {

    FirebaseAuth auth;
    AuthCredential credential;

    EditText currentPassword;
    EditText password;
    EditText confirmPassword;
    Button changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        auth = FirebaseAuth.getInstance();

        password = findViewById(R.id.password);
        currentPassword = findViewById(R.id.current_password);
        confirmPassword = findViewById(R.id.password_confirm);
        changePassword = findViewById(R.id.change_password);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    credential = EmailAuthProvider
                            .getCredential(auth.getCurrentUser().getEmail(), currentPassword.getText().toString());

                    auth.getCurrentUser().reauthenticate(credential)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    auth.getCurrentUser().updatePassword(password.getText().toString());
                                    Toast.makeText(ChangePasswordActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //Toast.makeText(ChangePasswordActivity.this, "Nahi Hua", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();

                                }
                            });
                }
            }
        });

    }

    private boolean validate () {
        if (password.getText().toString().isEmpty()) {
            password.setError("This can't be Empty");
            return false;
        } else if (currentPassword.getText().toString().isEmpty()) {
            currentPassword.setError("This can't be Empty");
            return false;
        } else if (confirmPassword.getText().toString().isEmpty()) {
            confirmPassword.setError("This can't be Empty");
            return false;
        } else if (currentPassword.getText().toString().equals(password.getText().toString())) {
            password.setError("New Password can't be same as your existing password");
            return false;
        } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            confirmPassword.setError("Passwords don't match");
            return false;
        } else {
            return true;
        }
    }
}
