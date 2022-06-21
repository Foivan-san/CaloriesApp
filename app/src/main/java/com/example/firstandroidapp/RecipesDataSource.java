package com.example.firstandroidapp;

import java.util.ArrayList;

public class RecipesDataSource {
    private ArrayList<Integer> photoPool;
    private ArrayList<Integer> descriptionPool;
    private ArrayList<Integer> dishesPool;

    //return photoPool
    public ArrayList<Integer> getPhotoPool() {
        return photoPool;
    }

    //return descriptionPool
    public ArrayList<Integer> getDescriptionPool() {
        return descriptionPool;
    }

    //return dishesPool
    public ArrayList<Integer> getDishesPool() {
        return dishesPool;
    }

    public void setupPhotoPool() {
        photoPool.add(R.drawable.dosa);
        photoPool.add(R.drawable.pasta);
        photoPool.add(R.drawable.chicken_roll);
    }

    public void setupDescriptionPool() {
        descriptionPool.add(R.string.description1);
        descriptionPool.add(R.string.description2);
        descriptionPool.add(R.string.description3);
    }

    public void setupDishesPool() {
        dishesPool.add(R.string.recipe1);
        dishesPool.add(R.string.recipe2);
        dishesPool.add(R.string.recipe3);
    }

    public RecipesDataSource(){
        photoPool = new ArrayList<Integer>();
        descriptionPool = new ArrayList<Integer>();
        dishesPool = new ArrayList<Integer>();
        setupPhotoPool();
        setupDishesPool();
        setupDescriptionPool();
    }

    public int getDataSourceLength(){
        return photoPool.size();

    }
}
