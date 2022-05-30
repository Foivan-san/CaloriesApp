package com.example.firstandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database
        DBAdapter db = new DBAdapter(this);
        db.open();

        //Count rows in table food
        int foodTableRows = db.countTableRows("food");

        if(foodTableRows < 1)
        {
            Toast.makeText(this, "Loading setup...", Toast.LENGTH_SHORT).show();
            DBSetupInsert setupInsert = new DBSetupInsert(this);
            setupInsert.insertAllFood();
            Toast.makeText(this, "Setup completed!", Toast.LENGTH_SHORT).show();
        }

        //Count row in table users
        int userTableRows = db.countTableRows("users");
        if(userTableRows < 1)
        {
            //Text before the sign up
            Toast.makeText(this, "Few steps away from sign up!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);
        }

        db.close();

    }


}