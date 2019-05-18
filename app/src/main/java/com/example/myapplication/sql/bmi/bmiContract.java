package com.example.myapplication.sql.bmi;

import android.provider.BaseColumns;

import java.text.SimpleDateFormat;
import java.util.Date;

public class bmiContract {
    private bmiContract(){}

    //inner class to define table contents for weight, age and height
    public static class bmiEntry implements BaseColumns{
        public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
         // java.util.Date
        public static final String _ID = "id";//TODO
        public static final String TABLE_NAME = "userDetails";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_HEIGHT = "height";
        public static final String COLUMN_NAME_DATE = "date";       //This is used for viewing the date
        public static final Date COLUMN_NAME_USEFUL_DATE = new Date();              //THis is used for manipulatiing the date


    }

}
