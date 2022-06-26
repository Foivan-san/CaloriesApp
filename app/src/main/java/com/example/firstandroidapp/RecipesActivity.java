package com.example.firstandroidapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipesActivity extends AppCompatActivity {

    ListView lvProgram;

    String[] programName = {"Healthy Vegan Burger", "Carrot cake", "Superfood cookies",
            "Goat with hummus", "Lentil salad with quinoa", "Croquettes with spinach"};
    String[] programDescription = {"Alternative low calorie burgers", "A healthy, nutritious, semi-sweet and filling carrot cake",
            "The ideal snack to have energy throughout the day",
            "Recipe for lovers of both meat and legumes", "Very healthy and nutritious salad, with quinoa, lentils and avocado",
            "Delicious and dietary croquettes with spinach and cheese"};
    int[] programImages = {R.drawable.burger, R.drawable.carrot_cake,
            R.drawable.cookies, R.drawable.kid, R.drawable.lentil_salad,
            R.drawable.spinac_croquettes};

    String[] urls = {
            "https://www.baskini.gr/blog/sintages/syntagi-enallaktika-mpergker-burger-chamiles-thermides/",
            "https://www.baskini.gr/blog/ygieino-keik-karotoy-healthy-carrot-cake/",
            "https://akispetretzikis.com/recipe/2646/superfood-cookies",
            "https://www.mednutrition.gr/portal/syntages/kreas/15954-katsikaki-me-xoymous",
            "https://www.argiro.gr/recipe/kinoa-me-fakes-avokanto-kai-chaloumi/",
            "https://www.argiro.gr/recipe/kroketes-me-spanaki/"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        lvProgram = findViewById(R.id.lvProgram);
        RecipesAdapter recipesAdapter = new RecipesAdapter(this, programName, programImages, programDescription, urls);

        lvProgram.setAdapter(recipesAdapter);
    }
}