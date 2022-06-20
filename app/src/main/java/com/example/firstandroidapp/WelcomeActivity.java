package com.example.firstandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class WelcomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.menu_drawer_open, R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true); //enable hamburger
        drawerToggle.syncState();

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_what_i_eat) {
            startActivity(new Intent(WelcomeActivity.this, FoodActivity.class));
        }
        else if (item.getItemId() == R.id.nav_recipe) {
            startActivity(new Intent(WelcomeActivity.this, RecipesActivity.class));
        }
        else if (item.getItemId() == R.id.nav_stats) {
            startActivity(new Intent(WelcomeActivity.this, StatsActivity.class));
        }
        else if (item.getItemId() == R.id.nav_qr) {
            startActivity(new Intent(WelcomeActivity.this, QrActivity.class));
        }
        else if (item.getItemId() == R.id.nav_logout) {
            startActivity(new Intent(WelcomeActivity.this, LogoutActivity.class));
        }
        return false;
    }
}