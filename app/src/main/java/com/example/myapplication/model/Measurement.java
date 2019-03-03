package com.example.myapplication.model;

import java.util.Date;

public class Measurement { //Date format: yyyy-MM-dd hh:mm:ss.SSS
    private static int id;
    private static String age;
    private static String weight;
    private static String height;
    private static Date date;


    public static Date getDate(){
        final Date date = new Date();
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public static String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
