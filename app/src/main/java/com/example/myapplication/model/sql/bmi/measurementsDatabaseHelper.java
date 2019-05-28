package com.example.myapplication.model.sql.bmi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.model.Measurement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class measurementsDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "measurements.db";
    public static final String TABLE_NAME = "measurements";

    public measurementsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE measurements (age INT, height INT, weight DOUBLE, activitylevels INT, bmi DOUBLE,bfp DOUBLE,date NOT NULL)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

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
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);

        String date = year + "-" + month + "-" + day;

        ContentValues values = new ContentValues();
        values.put("age", measurement1.getAge());
        values.put("height", measurement1.getHeight());
        values.put("weight", measurement1.getWeight());
        values.put("bmi", measurement1.getbmi());
        values.put("bfp", measurement1.getbfp());
        values.put("activitylevels", measurement1.getActivityLevels());
        values.put("date", date);

        db.insert("measurements", null, values);
        db.close();
    }

    public List<Measurement> getWeightReadings(String sDate, String eDate){
       /* final String queryWeight = "SELECT " +
                "weight " + "FROM " +
                "measurements " + "WHERE " +
                "date " + "BETWEEN " + sDate + " AND" +
                eDate + " ORDER BY date"; */

        String [] columns = {"weight"};

        String[] between ={"BETWEEN " +
                sDate + " AND "
                + eDate};

        String selection = "date=?";

        SQLiteDatabase db = this.getReadableDatabase();
        List<Measurement> weightList = new ArrayList<Measurement>();

        Cursor cursor = (Cursor) db.query("measurements",
                columns,
                selection,
                between,
                "",
                "",
                "date");

        if (cursor.moveToFirst()) {
            do {
                Measurement measurement = new Measurement();
                measurement.setWeight(cursor.getDouble(Integer.parseInt("weight")));
                // Adding user record to list
                weightList.add(measurement);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return weightList;

    }

    public List<Measurement> getbfpReadings(String sDate, String eDate){
        String [] columns = {"bfp"};

        String[] between ={"BETWEEN " +
                sDate + " AND "
                + eDate};

        SQLiteDatabase db = this.getReadableDatabase();
        List<Measurement> weightList = new ArrayList<Measurement>();

        Cursor cursor = (Cursor) db.query("measurements",
                columns,
                "date",
                between,
                "",
                "",
                "date");

        if (cursor.moveToFirst()) {
            do {
                Measurement measurement = new Measurement();
                measurement.setbfp(cursor.getInt(Integer.parseInt("bfp")));
                // Adding user record to list
                weightList.add(measurement);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return weightList;

    }

    public List<Measurement> getbmiReadings(String sDate, String eDate){
        String [] columns = {"bmi"};

        String[] between ={"BETWEEN " +
                sDate + " AND "
                + eDate};

        SQLiteDatabase db = this.getReadableDatabase();
        List<Measurement> weightList = new ArrayList<Measurement>();

        Cursor cursor = (Cursor) db.query("measurements",
                columns,
                "date",
                between,
                "",
                "",
                "date");

        if (cursor.moveToFirst()) {
            do {
                Measurement measurement = new Measurement();
                measurement.setbmi(cursor.getInt(Integer.parseInt("bmi")));
                // Adding user record to list
                weightList.add(measurement);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return weightList;
    }

    public List<Measurement> getDateReadings(String sDate, String eDate, String type){
        String [] columns = {"date"};

        String[] between ={"BETWEEN " +
                sDate + " AND "
                + eDate};

        SQLiteDatabase db = this.getReadableDatabase();
        List<Measurement> weightList = new ArrayList<Measurement>();

        Cursor cursor = (Cursor) db.query("measurements",
                columns,
                "date=?",
                between,
                "",
                "",
                "date");

        if (cursor.moveToFirst()) {
            do {
                Measurement measurement = new Measurement();
                measurement.setbmi(cursor.getInt(Integer.parseInt("bmi")));
                // Adding user record to list
                weightList.add(measurement);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return weightList;
    }

    public double getHeight(){
        String [] columns = {"height"};

        String[] between = {};

        SQLiteDatabase db = this.getReadableDatabase();
        double height= 0;

        Cursor cursor = (Cursor) db.query("measurements",
                columns,
                "",
                between,
                "",
                "",
                "");

        if (cursor.moveToFirst()) {
            do {
                Measurement measurement = new Measurement();
                measurement.setHeight(cursor.getInt(Integer.parseInt("bmi")));
                // Adding user record to list
                height = measurement.getHeight();
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return height;
    }


}
