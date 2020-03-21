package com.example.artcab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.artcab.fragments.IdeaFragment;
import com.example.artcab.fragments.JobsFragment;
import com.example.artcab.fragments.NetworksFragment;
import com.example.artcab.fragments.ProfileFragment;
import com.example.artcab.fragments.StudioFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}
