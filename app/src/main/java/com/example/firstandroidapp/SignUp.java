package com.example.firstandroidapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class SignUp  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        //DAY SPINNER
        ArrayList<String> days = new ArrayList<String>();
        for(int x=1; x<=31; x++)
        {
            days.add(Integer.toString(x));
        }
        Spinner spinnerDay = (Spinner) findViewById(R.id.spinnerDays);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);
        spinnerDay.setAdapter(adapter1);

        //YEAR SPINNER
        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);

        Spinner spinYear = (Spinner)findViewById(R.id.spinnerYears);
        spinYear.setAdapter(adapter2);
    }
}