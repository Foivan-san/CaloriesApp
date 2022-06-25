package com.example.firstandroidapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipesViewHolder {
    ImageView itemImage;
    TextView programTitle;
    TextView programDescription;
    // Get the handles by calling findViewById() on View object inside the constructor
    RecipesViewHolder(View v)
    {
        itemImage = v.findViewById(R.id.imageView);
        programTitle = v.findViewById(R.id.textView1);
        programDescription = v.findViewById(R.id.textView2);
    }
}
