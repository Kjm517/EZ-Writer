package com.ezwriter.speechtotext;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="register";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="Username";
    public static final String COL_3 ="Password";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Username TEXT,Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME); // DROP OLDER TABLE
        onCreate(db); //CREATED DB
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM register WHERE username=?", new String[]{username});

        if(c.getCount() > 0){
            return false;
        }else{
            return true;
        }
    }
}
