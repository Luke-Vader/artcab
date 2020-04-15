package com.example.artcab.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.artcab.R;
import com.example.artcab.fragments.IdeaFragment;
import com.example.artcab.fragments.JobsFragment;
import com.example.artcab.fragments.NetworksFragment;
import com.example.artcab.fragments.ProfileFragment;
import com.example.artcab.fragments.StudioFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel chanel =
                    new NotificationChannel("notifications", "notifications", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(chanel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Successful";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                    }
                });

        bottomNavigation = findViewById(R.id.bottom_nav);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NetworksFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.bottom_nav_network:
                            selectedFragment = new NetworksFragment();
                            break;

                        case R.id.bottom_nav_idea:
                            selectedFragment = new IdeaFragment();
                            break;

                        case R.id.bottom_nav_jobs:
                            selectedFragment = new JobsFragment();
                            break;

                        case R.id.bottom_nav_studio:
                            selectedFragment = new StudioFragment();
                            break;

                        case R.id.bottom_nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
