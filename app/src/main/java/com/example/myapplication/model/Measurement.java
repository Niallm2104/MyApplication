package com.example.myapplication.model;

import com.github.mikephil.charting.data.Entry;

public class Measurement extends Entry { //Date format: yyyy-MM-dd hh:mm:ss.SSS
    private static int id;
    private static int age;
    private static double weight;
    private static int height;
    private static String date;
    private static double bmi;
    private static double bfp;
    private static int activityLevels;

    public static double getbmi(){return bmi;}
    public void setbmi(double temp){bmi = temp;}

    public static double getbfp(){return bfp;}
    public void setbfp(double temp){bfp = temp;}


    public static String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getActivityLevels() {
        return activityLevels;
    }

    public void setActivityLevels(int activityLevels){this.activityLevels = activityLevels;}
}
