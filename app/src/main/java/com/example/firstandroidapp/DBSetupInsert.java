package com.example.firstandroidapp;

import android.content.Context;

public class DBSetupInsert {

    private final Context context;

    public DBSetupInsert(Context ctx)
    {
        this.context = ctx;
    }

    public void setupInsertToFood(String values)
    {
        DBAdapter db = new DBAdapter(context);
        db.open();
        db.insert("food", "food_name, food_cal, food_measurement", values);
        db.close();
    }

    public void insertAllFood()
    {
        //Μεσημεριανό και Βραδινό
        setupInsertToFood("'White Bread', '74', 'g'");
        setupInsertToFood("'Cucumber', '45', 'g'");
        setupInsertToFood("'Beef Burger', '168', 'g'");
        setupInsertToFood("'Tomato', '25', 'g'");
        setupInsertToFood("'Lettuce', '5', 'g'");
        setupInsertToFood("'Ground Beef', '344', 'g'");
        setupInsertToFood("'Chicken Leg', '266', 'g'");
        setupInsertToFood("'Yogurt 2%', '70', 'g'");
        setupInsertToFood("'Spaghetti', '330', 'g'");
        setupInsertToFood("'Pork Steak', '270', 'g'");
        setupInsertToFood("'Green Salad', '22', 'g'");
        setupInsertToFood("'Feta Cheese', '52', 'g'");
        setupInsertToFood("'Lentils', '226', 'g'");
        setupInsertToFood("'Parmesan Cheese', '65', 'g'");
        setupInsertToFood("'Cream Cheese', '177', 'g'");
        setupInsertToFood("'Balsamic Cream', '15', 'g'");
        setupInsertToFood("'Mushrooms', '44', 'g'");
        setupInsertToFood("'Bacon', '106', 'g'");
        setupInsertToFood("'Barley Juvette', '145', 'g'");
        setupInsertToFood("'Chicken Soup', '174', 'g'");
        setupInsertToFood("'Pies for Souvlaki', '225', 'g'");
        setupInsertToFood("'Minced Chicken', '474', 'g'");
        setupInsertToFood("'Omelette with Potatoes', '563', 'g'");
        setupInsertToFood("'Onion Raw', '16', 'g'");
        setupInsertToFood("'Potato Cooked', '52', 'g'");
        setupInsertToFood("'White Rice', '365', 'g'");
        setupInsertToFood("'Chicken Breast', '176', 'g'");
        setupInsertToFood("'Mashed Potatoes', '71', 'g'");
        setupInsertToFood("'Mayonnaise', '90', 'g'");
        setupInsertToFood("'Hamburger', '258', 'g'");
        setupInsertToFood("'Wholemeal Toast', '92', 'g'");
        setupInsertToFood("'French Bread Baguette', '130', 'g'");
        setupInsertToFood("'Tortilla', '120', 'g'");
        setupInsertToFood("'Pad Thai', '591', 'g'");
        setupInsertToFood("'Chicken Fajita', '156', 'g'");
        setupInsertToFood("'Salt & Vinegar Crisps', '130', 'g'");

//Πρωινό και Σνακ
        setupInsertToFood("'Banana', '120', 'g'");
        setupInsertToFood("'Honey', '70', 'g'");
        setupInsertToFood("'Boiled Egg', '71', 'g'");
        setupInsertToFood("'Strawberry', '4', 'g'");
        setupInsertToFood("'Greek Coffee plain', '1', 'g'");
        setupInsertToFood("'Almonds', '115', 'g'");
        setupInsertToFood("'Freddo Espresso plain', '3', 'g'");
        setupInsertToFood("'Quick Oats', '167', 'g'");
        setupInsertToFood("'Greek Coffee mediocre', '21', 'g'");
        setupInsertToFood("'Tahini with Honey', '86', 'g'");
        setupInsertToFood("'Cake', '140', 'g'");
        setupInsertToFood("'Fresh Milk', '138', 'g'");
        setupInsertToFood("'Cereals', '113', 'g'");
        setupInsertToFood("'Cappuccino plain', '32', 'g'");
        setupInsertToFood("'Chocolate ice cream', '125', 'g'");
        setupInsertToFood("'Vanilla ice cream', '137', 'g'");
        setupInsertToFood("'Lower Carb Protein', '75', 'g'");
        setupInsertToFood("'Apple', '95', 'g'");
        setupInsertToFood("'Coca Cola', '151', 'g'");
        setupInsertToFood("'Chocolate Milk', '75', 'g'");
        setupInsertToFood("'Pineapple', '45', 'g'");
    }
}
