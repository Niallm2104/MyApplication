
package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.myapplication.R;
import com.example.myapplication.model.Measurement;
import com.example.myapplication.sql.bmi.bmiDatabaseHelper;

import java.util.Calendar;


public class GraphInput extends AppCompatActivity {

    private BottomNavigationView navigation;
    private ConstraintLayout constaintLayout;
    private AppCompatActivity activity = this;
    private EditText startDate,endDate;
    private Button inputData;
    private Intent intent = new Intent(activity, graphActivity1.class);
    private CoordinatorLayout coord;
    private Calendar sCalendar = Calendar.getInstance();
    private Calendar eCalendar = Calendar.getInstance();
    bmiDatabaseHelper bmiDatabaseHelper;
    private String graphType;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_graph:
                    Intent gIntent = new Intent(activity, GraphInput.class);
                    startActivity(gIntent);
                    return true;
                case R.id.action_calculators:
                    Intent cIntent = new Intent(activity, Calculators.class);
                    startActivity(cIntent);
                    return true;
                case R.id.action_settings:
                    addMeasure();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        startDate = findViewById(R.id.sDate);
        endDate = findViewById(R.id.eDate);
        inputData = findViewById(R.id.inputRequests);
        coord = findViewById(R.id.graph_coordinator_layout);
    }

    private void initListeners(){
        inputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDateInputs();
            }
        });
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.weight_graph:
                if (checked){
                    graphType = "weight";
                }
                break;
            case R.id.bfp_graph:
                if(checked){
                    graphType = "bfp";
                }
                break;
            case R.id.bmi_graph:
                if(checked){
                    graphType = "bmi";
                }
                break;
        }

    }

    private void initObjects(){
        bmiDatabaseHelper = new bmiDatabaseHelper(activity);
    }

    private void checkDateInputs(){
        String sDate = startDate.getText().toString().trim();
        String eDate = endDate.getText().toString().trim();


        String[] startD = sDate.split("-");                         //form an array of strings split using a hyphon this
        String[] endD = eDate.split("-");                           //will give an array with the first value day, second a month, and the third a year


        int sday = Integer.parseInt(startD[0]);
        int sMonth = Integer.parseInt(startD[1]);
        int sYear = Integer.parseInt(startD[2]);
        int eDay = Integer.parseInt(endD[0]);
        int eMonth = Integer.parseInt(endD[1]);
        int eYear = Integer.parseInt(endD[2]);

        if(checkIfDay(sday) && checkIfMonth(sMonth) && checkIfYear(sYear)){
            sCalendar.set(sYear, sMonth, sday);
        } else Snackbar.make(coord, R.string.dateErrorMessage, Snackbar.LENGTH_LONG).show();


        if(checkIfDay(eDay) && checkIfMonth(eMonth) && checkIfYear(eYear)){
            eCalendar.set(eDay, eMonth, eDay);
            intent.putExtra("eCal", eCalendar);
            checkDates(sCalendar, eCalendar);
        }else Snackbar.make(coord, R.string.dateErrorMessage, Snackbar.LENGTH_LONG).show(); //Todo Snackbar to request correct date


    }

    private void addMeasure(){
        Measurement m1 = new Measurement();
        Calendar c1 = Calendar.getInstance();
        c1.set(2019, 01,26);
        long temp = c1.getTimeInMillis();
        m1.setDate(temp);
        m1.setAge(22);
        m1.setWeight(87);

        Measurement m2 = new Measurement();
        Calendar c2 = Calendar.getInstance();
        c2.set(2019, 01,30);
        temp = c2.getTimeInMillis();
        m2.setDate(temp);
        m2.setAge(22);
        m2.setWeight(86);

        Measurement m3 = new Measurement();
        Calendar c3 = Calendar.getInstance();
        c3.set(2019, 02,05);
        temp = c3.getTimeInMillis();
        m3.setDate(temp);
        m3.setAge(22);
        m3.setWeight(84);

        Measurement m4 = new Measurement();
        Calendar c4 = Calendar.getInstance();
        c4.set(2019, 02,10);
        temp = c4.getTimeInMillis();
        m4.setDate(temp);
        m4.setAge(22);
        m4.setWeight(83);

        bmiDatabaseHelper.addMeasurement(m1);
        bmiDatabaseHelper.addMeasurement(m2);
        bmiDatabaseHelper.addMeasurement(m3);
        bmiDatabaseHelper.addMeasurement(m4);
    }

    private boolean checkIfDay(int s){  //Todo create better date checkers than these
        if(s > 0 && s < 32){
            return true;
        }
        else return false;
    }

    private boolean checkIfMonth(int s){

        if(s > 0 && s < 13){ return true;
        }else return false;
    }

    private boolean checkIfYear(int s){
        if(s > 1901 && s < 3000){
            return true;
        }else return false;
    }

    private void checkDates(Calendar s, Calendar e){
        if(s!= null && e != null){
            Snackbar.make(coord, "this", Snackbar.LENGTH_LONG).show();
            Intent intent2 = new Intent(activity, graphActivity1.class);

            long startDate = sCalendar.getTimeInMillis();
            long endDate = eCalendar.getTimeInMillis();

            intent2.putExtra("sCal", startDate);//TODO
            intent2.putExtra("eCal", endDate);//TODO
            intent2.putExtra("type", graphType);
            startActivity(intent2);                    //if the calendar objects have been initialised go to display graphs
        }
    }
}
