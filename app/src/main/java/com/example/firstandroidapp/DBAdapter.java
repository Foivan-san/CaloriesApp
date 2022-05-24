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
                db.execSQL("CREATE TABLE IF NOT EXISTS food (" +
                        "   food_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                        "   food_name VARCHAR(255) NOT NULL," +
                        "   food_cal FLOAT NOT NULL," +
                        "   food_measurement VARCHAR(255) NOT NULL" +
                        ");");

                db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                        "   user_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                        "   user_email VARCHAR(255) NOT NULL," +
                        "   user_nickname VARCHAR(255), " +
                        "   user_password VARCHAR(255) NOT NULL," +
                        "   user_dob DATE NOT NULL," +
                        "   user_gender INT," +
                        "   user_height INT NOT NULL," +
                        "   user_weight INT NOT NULL," +
                        "   user_target_weight INT NOT NULL," +
                        "   user_last_seen TIME NOT NULL);");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS food");
            db.execSQL("DROP TABLE IF EXISTS users");
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

    //Count-----------------------------------------------------------------------------------------
    public int countTableRows(String table)
    {
        Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM " + table + "", null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }
    //----------------------------------------------------------------------------------------- Count
}
