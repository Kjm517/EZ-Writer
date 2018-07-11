package com.ezwriter.speechtotext;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "register";
    public static final String COL_1 = "user_id";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME); //Drop older table if exists
        onCreate(db);
    }

    public boolean addData(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

//    /**
//     * Returns all the data from database
//     * @return
//     */
//    public Cursor getData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT * FROM " + TABLE_NAME;
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }
//
//    public boolean checkUser(String userName, String password) {
//        boolean ret = false;
//        // 1. get reference to readable DB
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor;
//        cursor = db.rawQuery("SELECT *FROM " + TABLE_NAME + " WHERE " + COL_2 + "=? AND " + COL_3 + "=?", new String[]{userName, password});
//        if (cursor != null) {
//            if (cursor.getCount() > 0) {
//                ret = true;
//            } else {
//                ret = false;
//            }
//        }
//
//        return ret;
//    }

}
