package com.example.firstandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.firstandroidapp.databinding.ActivityFoodBinding;

public class FoodActivity extends DrawerActivity {

    ActivityFoodBinding activityFoodBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFoodBinding = ActivityFoodBinding.inflate(getLayoutInflater());
        setContentView(activityFoodBinding.getRoot());
        allocateActivityTitle("Food");
    }
}