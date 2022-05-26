package com.example.firstandroidapp;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class mymenu extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.home:
                Toast.makeText(this, "home clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.first:
                Toast.makeText(this, "first click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:

        }
        return super.onOptionsItemSelected(item);

    }
}
//str