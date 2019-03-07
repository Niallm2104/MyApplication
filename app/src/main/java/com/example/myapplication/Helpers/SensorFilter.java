package com.example.myapplication.Helpers;

public class SensorFilter {
    private SensorFilter(){}

    public static float sum(float[] array){
        float retval = 0;
        for(int i = 0; i< array.length; i++){
            retval += array[i];
        }
        return retval;
    }

    public static float[] cross(float[] arrayA, float[] arrayB){
        float[] retArray = new float[3];
        retArray[0] = arrayA[1] *arrayB[2] - arrayA[2] * arrayB[1];
        retArray[1] = arrayA[2] *arrayB[0] - arrayA[0] * arrayB[2];
        retArray[2] = arrayA[0] *arrayB[1] - arrayA[1] * arrayB[0];
        return retArray;
    }

    public static float norm(float[] array){
        float retVal = 0;
        for(int i=0; i<array.length; i++){
            retVal += array[i] * array[i];
        }
        return (float) Math.sqrt(retVal);
    }

    public static float dot(float[] a, float[] b){
        float retVal = a[0] * b[0] +a[1] * b[1] +a[2] *b[2];
        return retVal;
    }

    public static float[] normalize(float[] a){
        float[] retVal = new float[a.length];
        float norm = norm(a);
        for (int i = 0; i <a.length; i++){
            retVal[i] = a[i]/norm;
        }
        return retVal;
    }
}
