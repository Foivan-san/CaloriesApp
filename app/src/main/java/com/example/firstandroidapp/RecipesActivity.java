package com.example.firstandroidapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipesActivity extends AppCompatActivity {

    private RecipesDataSource ds;
    private ListView recipesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        ds = new RecipesDataSource();
        recipesListView = (ListView)findViewById(R.id.listView1);
        recipesListView.setAdapter(new RecipesDataSourceAdapter(this,ds));

    }
}