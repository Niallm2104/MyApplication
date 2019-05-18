package com.example.myapplication.model;

import com.github.mikephil.charting.data.Entry;

import java.util.Date;

public class Measurement extends Entry { //Date format: yyyy-MM-dd hh:mm:ss.SSS
    private static int id;
    private static int age;
    private static int weight;
    private static int height;
    private static long date;


    public static Date getDate(){
        final Date date = new Date();
        return date;
    }

    public void setDate(long date){
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

    public static int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public static int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
