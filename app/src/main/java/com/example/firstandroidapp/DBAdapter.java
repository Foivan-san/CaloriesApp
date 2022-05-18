package com.example.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBAdapter {

    //Variables-------------------------------------------------------------------------------------
    private static final String databaseName = "caloriesApp";
    private static final int databaseVersion = 1;
    //-------------------------------------------------------------------------------------Variables

    //Database Variables----------------------------------------------------------------------------
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    //----------------------------------------------------------------------------Database Variables

    //Class DbAdapter-------------------------------------------------------------------------------
    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    //-------------------------------------------------------------------------------Class DbAdapter

    //DatabaseHelper--------------------------------------------------------------------------------
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, databaseName, null, databaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try
            {
                //Create tables
                db.execSQL("CREATE TABLE IF NOT EXISTS BREAKFAST (" +
                        "   BREAKFAST_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                        "   BREAKFAST_NAME VARCHAR(255) NOT NULL," +
                        "   BREAKFAST_CAL FLOAT NOT NULL" +
                        ");");

                db.execSQL("CREATE TABLE IF NOT EXISTS DECATIAN (" +
                        "   DECATIAN_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                        "   DECATIAN_NAME VARCHAR(255) NOT NULL," +
                        "   DECATIAN_CAL FLOAT NOT NULL" +
                        ");");

                db.execSQL("CREATE TABLE IF NOT EXISTS LUNCH (" +
                        "   LUNCH_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                        "   LUNCH_NAME VARCHAR(255) NOT NULL," +
                        "   LUNCH_CAL FLOAT NOT NULL" +
                        ");");

                db.execSQL("CREATE TABLE IF NOT EXISTS AFTERNOON (" +
                        "   AFTERNOON_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                        "   AFTERNOON_NAME VARCHAR(255) NOT NULL," +
                        "   AFTERNOON_CAL FLOAT NOT NULL" +
                        ");");

                db.execSQL("CREATE TABLE IF NOT EXISTS DINNER (" +
                        "   DINNER_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                        "   DINNER_NAME VARCHAR(255) NOT NULL," +
                        "   DINNER_CAL FLOAT NOT NULL" +
                        ");");

                db.execSQL("CREATE TABLE IF NOT EXISTS SNACK (" +
                        "   SNACK_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                        "   SNACK_NAME VARCHAR(255) NOT NULL," +
                        "   SNACK_CAL FLOAT NOT NULL" +
                        ");");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS BREAKFAST");
            db.execSQL("DROP TABLE IF EXISTS DECATIAN");
            db.execSQL("DROP TABLE IF EXISTS LUNCH");
            db.execSQL("DROP TABLE IF EXISTS AFTERNOON");
            db.execSQL("DROP TABLE IF EXISTS DINNER");
            db.execSQL("DROP TABLE IF EXISTS SNACK");
            onCreate(db);

            String TAG = "Tag";
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + " , which will destroy all old data");
        }
    }
    //--------------------------------------------------------------------------------DatabaseHelper

    //Open database
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //---------------------------------------------------------------------------------Open database

    //Close database--------------------------------------------------------------------------------
    public void close()
    {
        DBHelper.close();
    }
    //--------------------------------------------------------------------------------Close database

    //Insert data-----------------------------------------------------------------------------------
    public void insert(String table, String fields, String values)
    {
        db.execSQL("INSERT INTO " + table + "(" + fields + ") VALUES (" + values + ")");
    }
    //-----------------------------------------------------------------------------------Insert data

}
