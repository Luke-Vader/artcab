package com.example.artcab.components;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.artcab.ChangePasswordActivity;
import com.example.artcab.EditUserActivity;
import com.example.artcab.MainActivity;
import com.example.artcab.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileBottomSheet extends BottomSheetDialogFragment {

    FirebaseAuth auth;

    LinearLayout editProfile;
    LinearLayout logout;
    LinearLayout viewPosts;
    LinearLayout changePassword;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View optionView = View.inflate(getContext(), R.layout.profile_bottom_sheet, null);
        dialog.setContentView(optionView);

        auth = FirebaseAuth.getInstance();

        editProfile = optionView.findViewById(R.id.edit_profile);
        logout = optionView.findViewById(R.id.logout);
        viewPosts = optionView.findViewById(R.id.view_posts);
        changePassword = optionView.findViewById(R.id.change_password);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditUserActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        viewPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Yet to be built", Toast.LENGTH_SHORT).show();
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
            }
        });

    }
}
