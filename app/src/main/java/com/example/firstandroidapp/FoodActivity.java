package com.example.firstandroidapp;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import java.util.ArrayList;
import java.util.Locale;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu);

            MenuItem searchItem = menu.findItem(R.id.item_search);
            SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    ArrayList<String> userlist = new ArrayList<>();

                    for (String user : listItem){
                        if (user.toLowerCase().contains(newText.toLowerCase())){
                            userlist.add(user);
                        }
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(FoodActivity.this,
                            android.R.layout.simple_list_item_1, userlist);
                    userlist.setAdapter(adapter);

                    return true;
                }
            });



        }
    }
}