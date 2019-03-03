package com.example.myapplication.Helpers;

import android.content.Context;


public class Equations {

    private Context context;

    public Equations(Context context){ this.context = context;}

    public int calculateBMI(int weight, int height){
        if (height == 0 || weight == 0) {
            return 0;
        }
        int h = height * height;
        int bmi = weight/h;

        if(bmi != 0){
            return bmi;
        }
        return 0;
    }

}
