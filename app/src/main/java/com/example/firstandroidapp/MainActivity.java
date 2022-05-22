package com.example.firstandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
        int tableRows = db.countTableRows("food");

        if(tableRows < 1)
        {
            DBSetupInsert setupInsert = new DBSetupInsert(this);
            setupInsert.insertAllFood();
        }

        db.close();

    }
}