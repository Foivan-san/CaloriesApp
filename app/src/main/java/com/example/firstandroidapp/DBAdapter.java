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
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            //db.execSQL("DROP TABLE IF EXISTS " + databaseTableNotes);
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
}
