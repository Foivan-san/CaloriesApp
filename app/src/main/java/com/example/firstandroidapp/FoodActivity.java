package com.example.firstandroidapp;


import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {


    DBAdapter dbAdapter;
    ListView food_list;
    ArrayList<String> listItem;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        /*
        food_list = findViewById(R.id.food_list);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, listItem);
        food_list.setAdapter(adapter);
         */

        dbAdapter = new DBAdapter(this);

        listItem = new ArrayList<String>();
        food_list = findViewById(R.id.food_list);

        viewData();

        food_list.setOnItemClickListener((adapterView, view, i, l) -> {
            String text = food_list.getItemAtPosition(i).toString();
            Toast.makeText(FoodActivity.this, ""+text, Toast.LENGTH_SHORT).show();
        });


    }

    private void viewData() {
        Cursor cursor = dbAdapter.ViewDataFood();
        if(cursor.getCount()==0){
            Toast.makeText(FoodActivity.this, "No food to show", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                listItem.add(cursor.getString( 1));
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, listItem);
            food_list.setAdapter(adapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.done_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if (id == R.id.item_done){
            String itemSelected = "Selected items: \n";
            for (int i = 0; i <food_list.getCount(); i++){
                if (food_list.isItemChecked(i)){
                    itemSelected += food_list.getItemAtPosition(i) + "\n";
                }
            }
            Toast.makeText(this,itemSelected,Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}