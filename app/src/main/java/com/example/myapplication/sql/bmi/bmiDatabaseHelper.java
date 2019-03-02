package com.example.myapplication.sql.bmi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bmiDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bmi.db";

    public bmiDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + bmiContract.bmiEntry.TABLE_NAME + " (" +
                    bmiContract.bmiEntry._ID + " INTEGER PRIMARY KEY," +
                    bmiContract.bmiEntry.COLUMN_NAME_AGE + " TEXT," +
                    bmiContract.bmiEntry.COLUMN_NAME_HEIGHT + " TEXT," +
                    bmiContract.bmiEntry.COLUMN_NAME_WEIGHT + " TEXT" +
                    ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + bmiContract.bmiEntry.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(SQL_DELETE_ENTRIES);

        // Create tables again
        onCreate(db);

    }
}
