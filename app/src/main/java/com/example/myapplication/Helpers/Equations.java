package com.example.myapplication.Helpers;

import android.content.Context;


public class Equations {

    private Context context;
    private double BMR;
    private double BFP;
    private double fat;
    private double protein;
    private double carb;

    public Equations(){
    }

    public double calculateBFP( boolean maleF,double tricep, double aboveKnee, double abdominal, double hip, int age){
        double bodyDensity;
        double sum =  (tricep + aboveKnee + abdominal + hip);
        double square = sum * sum;

        if(maleF == true){
            bodyDensity = (0.29288 * sum) - (0.0005 * square) +        // Jackson/ Pollock formula for calculating BFP
                    (0.15845 * age) - 5.76377;                         //Using measurements from tricep, hip, abdominal, and
            BFP = (495/bodyDensity) - 450;                             //Thigh just above the knee
            return BFP;
        } else {
            bodyDensity = (0.29669 * sum) - (0.00043 * square) +
                    (0.02963 * age) + 1.4072;
            BFP = (495/bodyDensity) - 450;
            return BFP;
        }
    }

    public float calculateBMI(float weight, float height){
        float hi = height/100;
        float h = (hi * hi);
        return weight/h;
    }

    public int calculateBMR(int age, int weight,int height, int activityLevels, boolean maleF){ //BMR calculator
        double BMRhelp;

        if(maleF == true){
            BMRhelp = (weight * 10) +(6.25 * height) - (5 * age) + 5;
            } else BMRhelp = (weight * 10) +(6.25 * height) - (5 * age) - 161;

        switch(activityLevels){
            case 1:
                BMR = BMRhelp * 1.2;
                break;
            case 2:
                BMR = BMRhelp * 1.375;
                break;
            case 3:
                BMR = BMRhelp * 1.55;
                break;
            case 4:
                BMR = BMRhelp * 1.725;
                break;
            case 5:
                BMR = BMRhelp * 1.9;
                break;
        }
        return (int) BMR;
    }

    public void macroRecommender(int BMR){  //Split daily calories into protein, fat and carbs
        double cals;
        double calsPC;
        cals = BMR * 0.4;
        carb = cals/4;
        calsPC = BMR * 0.3;
        protein = calsPC/4;
        fat = calsPC/9;
    }

    public double getFat() {
        return fat;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarb() {
        return carb;
    }

}
