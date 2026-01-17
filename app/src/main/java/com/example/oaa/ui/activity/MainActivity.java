package com.example.oaa.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.oaa.ui.fragment.HomeFragment;
import com.example.oaa.R;


import com.example.oaa.ui.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FrameLayout container;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        bottomNav = findViewById(R.id.bottom_nav);

        // 默认显示 Home
        setFragment(HomeFragment.newInstance("Android"));

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                setFragment(HomeFragment.newInstance("Android"));
            } else if (id == R.id.nav_favorites) {
                setFragment(HomeFragment.newInstance("Favorites"));
            } else if (id == R.id.nav_profile) {
                setFragment(ProfileFragment.newInstance());
                //setFragment(HomeFragment.newInstance("Profile"));
            }
            return true;
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}
