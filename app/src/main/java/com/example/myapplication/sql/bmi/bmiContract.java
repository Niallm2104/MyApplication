package com.example.myapplication.sql.bmi;
import android.provider.BaseColumns;

public class bmiContract {
    private bmiContract(){}

    //inner class to define table contents for weight, age and height
    public static class bmiEntry implements BaseColumns{
        public static final String TABLE_NAME = "userDetails";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_HEIGHT = "height";

    }

}
