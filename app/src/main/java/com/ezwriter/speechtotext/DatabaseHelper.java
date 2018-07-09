package com.ezwriter.speechtotext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String INTEGER_TYPE = " INTEGER";
    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "users_table";


    //Table Columns
    public static String COL_1 = "user_id";
    public static String COL_2 = "username";
    public static String COL_3 = "password";

    public String[] COLUMNS = {COL_1, COL_2, COL_3};
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_1 + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEP +
                    COL_2 + TEXT_TYPE + COMMA_SEP +
                    COL_3 + TEXT_TYPE + " )";

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addData(String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true; //returns all data from the database
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean checkUser(String uname, String pass){
        boolean ret = false;

        // Get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + "=? AND " + COL_3 + "=?", new String[]{uname, pass});
        if(cursor != null){
            if(cursor.getCount() > 0){
                ret = true;
            }else{
                ret = false;
            }
        }
        return ret;
    }
}
