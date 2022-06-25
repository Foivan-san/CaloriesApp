package com.example.firstandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    //Database variables
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    private static final String DBNAME = "CaloriesApp.db";

    //--------------------------------class DbAdapter-----------------------------------------------
    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                //Create tables
                db.execSQL("CREATE TABLE IF NOT EXISTS food (" +
                        "   food_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                        "   food_name VARCHAR(255) NOT NULL," +
                        "   food_cal FLOAT NOT NULL," +
                        "   food_measurement VARCHAR(255) NOT NULL" +
                        ");");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                db.execSQL("create Table users(username TEXT primary key, password TEXT)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS food");
            db.execSQL("DROP TABLE IF EXISTS users");
            onCreate(db);

            String TAG = "Tag";
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + " , which will destroy all old data");
        }
    }

        //---------------------------------Open Database--------------------------------------------
        public DBAdapter open() throws SQLException {
            db = DBHelper.getWritableDatabase();
            return this;
        }

        //----------------------------------Close Database------------------------------------------
        public void close() { DBHelper.close();}

        //-----------------------------------Insert data--------------------------------------------
        public void insert(String table, String fields, String values)
        {
            db.execSQL("INSERT INTO " + table + "(" + fields + ") VALUES (" + values + ")");
        }

        public Boolean insertData(String username, String password) {
            SQLiteDatabase MyDB = this.DBHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", username);
            contentValues.put("password", password);
            long result = MyDB.insert("users", null, contentValues);
            if (result == -1) return false;
            else
                return true;
        }

        public Boolean checkUsername(String username) {
            SQLiteDatabase MyDB = this.DBHelper.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
            if (cursor.getCount() > 0)
                return true;
            else
                return false;
        }

        public Boolean checkUsernamePassword(String username, String password) {
            SQLiteDatabase MyDB = this.DBHelper.getWritableDatabase();
            Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
            if (cursor.getCount() > 0)
                return true;
            else
                return false;
        }

        //Count-----------------------------------------------------------------------------------------
        public int countTableRows(String table) {
            SQLiteDatabase db = this.DBHelper.getWritableDatabase();
            Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM " + table + "", null);
            mCount.moveToFirst();
            int count = mCount.getInt(0);
            mCount.close();
            return count;
        }
        //----------------------------------------------------------------------------------------- Count


        //Insert data for food page-----------------------------------------------------------------
        public Boolean insertDataFood(String food_name) {
            SQLiteDatabase MyDB = this.DBHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("food_name", food_name);
            long result = MyDB.insert("food", null, contentValues);
            if (result == -1) return false;
            else
                return true;
        }
        //------------------------------------------------------------------------------------------

        //View data food----------------------------------------------------------------------------
        public Cursor ViewDataFood(){
            SQLiteDatabase db = this.DBHelper.getReadableDatabase();
            String query = "SELECT * FROM food";
            Cursor cursor = db.rawQuery(query, null);

            return cursor;
        }
}
