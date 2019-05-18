package com.example.myapplication.sql.bmi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.model.Measurement;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class bmiDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "bmi.db";
    public static Date uDate = convertUtilToSql(bmiContract.bmiEntry.COLUMN_NAME_USEFUL_DATE);
    private int weight, height, age;

    public bmiDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + bmiContract.bmiEntry.TABLE_NAME + " (" +
                    bmiContract.bmiEntry._ID + " INTEGER PRIMARY KEY," +
                    "userNumber" + " TEXT," +
                    bmiContract.bmiEntry.COLUMN_NAME_AGE + " TEXT," +
                    bmiContract.bmiEntry.COLUMN_NAME_HEIGHT + " TEXT," +
                    bmiContract.bmiEntry.COLUMN_NAME_WEIGHT + " FLOAT," +
                    bmiContract.bmiEntry.COLUMN_NAME_DATE+ "TEXT" +        //Date format: long milliseconds for ease of querying
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
        //values.put("userNumber", measurement1.getId());
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

    public List<Measurement> getWeightReadings(long sDate, long eDate) {
        // array of columns to fetch
        String[] columns = {
                bmiContract.bmiEntry.COLUMN_NAME_WEIGHT
        };
        // sorting orders
        String sortOrder =
                bmiContract.bmiEntry.COLUMN_NAME_WEIGHT + " ASC";
        List<Measurement> weightList = new ArrayList<Measurement>();
        //WHERE clause
        String where =
                bmiContract.bmiEntry.COLUMN_NAME_DATE;

        String[] between ={"BETWEEN " +
                sDate + " AND "
                        + eDate};

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use com.example.myapplication.sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(bmiContract.bmiEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                where,        //columns for the WHERE clause
                between,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Measurement measurement = new Measurement();
                measurement.setWeight(cursor.getInt(Integer.parseInt(bmiContract.bmiEntry.COLUMN_NAME_WEIGHT)));
                // Adding user record to list
                weightList.add(measurement);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return weightList;
    }

    public List<Measurement> getDateofWeightReadings(long sDate, long eDate) {
        // array of columns to fetch
        String[] columns = {
                bmiContract.bmiEntry.COLUMN_NAME_DATE
        };
        // sorting orders
        String sortOrder =
                bmiContract.bmiEntry.COLUMN_NAME_DATE + " ASC";
        List<Measurement> dateList = new ArrayList<Measurement>();
        //WHERE clause
        String where =
                bmiContract.bmiEntry.COLUMN_NAME_DATE;

        String[] between ={"BETWEEN " +
                sDate + " AND "
                + eDate};

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use com.example.myapplication.sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(bmiContract.bmiEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                where,        //columns for the WHERE clause
                between,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Measurement measurement = new Measurement();
                measurement.setDate(cursor.getInt(Integer.parseInt(bmiContract.bmiEntry.COLUMN_NAME_DATE)));
                // Adding user record to list
                dateList.add(measurement);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return dateList;
    }

    public int getWeight(){

        // array of columns to fetch
        String[] columns = {
                bmiContract.bmiEntry.COLUMN_NAME_WEIGHT
        };
        // sorting orders
        String sortOrder =
                bmiContract.bmiEntry.COLUMN_NAME_WEIGHT + " ASC";
        //WHERE clause
        String where =
                bmiContract.bmiEntry.COLUMN_NAME_WEIGHT;

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use com.example.myapplication.sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(bmiContract.bmiEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                where,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Measurement measurement = new Measurement();
                measurement.setWeight(cursor.getInt(Integer.parseInt(bmiContract.bmiEntry.COLUMN_NAME_WEIGHT)));
                // Adding user record to list
                weight = measurement.getWeight();
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return weight;

    }

    public int getHeight(){
        // array of columns to fetch
        String[] columns = {
                bmiContract.bmiEntry.COLUMN_NAME_HEIGHT
        };
        // sorting orders
        String sortOrder =
                bmiContract.bmiEntry.COLUMN_NAME_HEIGHT + " ASC";
        //WHERE clause
        String where =
                bmiContract.bmiEntry.COLUMN_NAME_HEIGHT;

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use com.example.myapplication.sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(bmiContract.bmiEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                where,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Measurement measurement = new Measurement();
                measurement.setHeight(cursor.getInt(Integer.parseInt(bmiContract.bmiEntry.COLUMN_NAME_HEIGHT)));
                // Adding user record to list
                height = measurement.getHeight();
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return height;
    }

    public int getAge(){

        // array of columns to fetch
        String[] columns = {
                bmiContract.bmiEntry.COLUMN_NAME_AGE
        };
        // sorting orders
        String sortOrder =
                bmiContract.bmiEntry.COLUMN_NAME_AGE + " ASC";
        //WHERE clause
        String where =
                bmiContract.bmiEntry.COLUMN_NAME_AGE;

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use com.example.myapplication.sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(bmiContract.bmiEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                where,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Measurement measurement = new Measurement();
                measurement.setAge(cursor.getInt(Integer.parseInt(bmiContract.bmiEntry.COLUMN_NAME_AGE)));
                // Adding user record to list
                age = measurement.getAge();
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return age;
    }

}
