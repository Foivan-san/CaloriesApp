package com.example.firstandroidapp;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    DBAdapter dbAdapter;
    Button add_food_btn;
    EditText add_food;

    ListView food_list;

    ArrayList<String> listItem;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        dbAdapter = new DBAdapter(this);

        listItem = new ArrayList<String>();

        add_food_btn = findViewById(R.id.add_food_btn);
        add_food = findViewById(R.id.add_food);
        food_list = findViewById(R.id.food_list);

        viewData();

        food_list.setOnItemClickListener((adapterView, view, i, l) -> {
            String text = food_list.getItemAtPosition(i).toString();
            Toast.makeText(FoodActivity.this, ""+text, Toast.LENGTH_SHORT).show();
        });

        add_food_btn.setOnClickListener(v -> {
            String foodItem = add_food.getText().toString();
            if(!foodItem.equals("") && dbAdapter.insertDataFood(foodItem)){
                Toast.makeText(FoodActivity.this, "Food added", Toast.LENGTH_SHORT).show();
                add_food.setText("");
            }
            else{
                Toast.makeText(FoodActivity.this, "Food not added", Toast.LENGTH_SHORT).show();
            }
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

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            food_list.setAdapter(adapter);
        }
    }


}