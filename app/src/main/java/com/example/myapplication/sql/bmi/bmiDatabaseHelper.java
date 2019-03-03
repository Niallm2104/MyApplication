package com.example.myapplication.sql.bmi;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.model.Measurement;

import java.sql.Date;

public class bmiDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bmi.db";
    public static Date uDate = convertUtilToSql(bmiContract.bmiEntry.COLUMN_NAME_USEFUL_DATE);

    public bmiDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + bmiContract.bmiEntry.TABLE_NAME + " (" +
                    bmiContract.bmiEntry._ID + " INTEGER PRIMARY KEY," +
                    "userNumber" + " TEXT," +
                    bmiContract.bmiEntry.COLUMN_NAME_AGE + " TEXT," +
                    bmiContract.bmiEntry.COLUMN_NAME_HEIGHT + " TEXT," +
                    bmiContract.bmiEntry.COLUMN_NAME_WEIGHT + " TEXT," +
                    bmiContract.bmiEntry.COLUMN_NAME_DATE+ "TEXT" +        //Date format: yyyy-MM-dd hh:mm:ss.SSS
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

    public void addMeasurement(Measurement measurement) {
        SQLiteDatabase db = this.getWritableDatabase();
        Measurement measurement1 = measurement;
        Date finalDate = convertUtilToSql(measurement1.getDate());


        ContentValues values = new ContentValues();
        values.put("userNumber", measurement1.getId());
        values.put(bmiContract.bmiEntry.COLUMN_NAME_AGE, measurement1.getAge());
        values.put(bmiContract.bmiEntry.COLUMN_NAME_DATE, finalDate.toString());
        values.put(bmiContract.bmiEntry.COLUMN_NAME_HEIGHT, measurement1.getHeight());
        values.put(bmiContract.bmiEntry.COLUMN_NAME_WEIGHT, measurement1.getWeight());


        // Inserting Row
        db.insert(bmiContract.bmiEntry.TABLE_NAME, null, values);
        db.close();
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {       //Method for converting java.util.Date to java.sql.Date
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
