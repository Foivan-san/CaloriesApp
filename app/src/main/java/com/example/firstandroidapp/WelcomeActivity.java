package com.example.firstandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.firstandroidapp.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends DrawBaseActivity {

    ActivityWelcomeBinding activityWelcomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWelcomeBinding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Welcome");
        setContentView(activityWelcomeBinding.getRoot());
    }
}