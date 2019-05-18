package com.example.myapplication.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.Measurement;
import com.example.myapplication.sql.bmi.bmiDatabaseHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class graphActivity1 extends AppCompatActivity {

        private LineChart lineChart;
        private com.example.myapplication.sql.bmi.bmiDatabaseHelper bmiDatabaseHelper;
        private final AppCompatActivity activity = com.example.myapplication.activities.graphActivity1.this;
        private ArrayList<String> xAxis;
        private ArrayList<Entry> yAxis;
        private List<Measurement> list, dateList;
        private long startDate, endDate;          //TODO should be long
        private CoordinatorLayout coords;
        private String type;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_graph1);

            initViews();
            initListeners();
            initObjects();

            Intent intent = getIntent();
            startDate = intent.getLongExtra("sCal", 0); //Get start and end date for the calendar
            endDate = intent.getLongExtra("eCal", 0);
            type = intent.getStringExtra("type");

            Snackbar.make(coords, String.valueOf(endDate),Snackbar.LENGTH_LONG).show();
            ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

            LineDataSet lineDataSet1 = new LineDataSet(yAxis, "Weights");
            lineDataSet1.setDrawCircles(false);
            lineDataSet1.setColor(Color.BLUE);

         //   if(type == "weight") {
                list = bmiDatabaseHelper.getWeightReadings(startDate, endDate);
                dateList = bmiDatabaseHelper.getDateofWeightReadings(startDate, endDate);
           /* }else if(type == "bfp"){
                list = bmiDatabaseHelper.getBFPReadings(startDate, endDate); //TODO
                dateList = bmiDatabaseHelper.getBFPReadings(startDate, endDate);
            } else if(type == "bmi"){
                list = bmiDatabaseHelper.getBMIReadings(startDate, endDate);
                dateList = bmiDatabaseHelper.getBMIReadings(startDate, endDate);
            }*/
            for(int i = 0; i < list.size(); i++){ //TODO check if this works may have to parse to float
                yAxis.add(list.get(i));         //add yAxis entrys from bmi Database
            }

            for(int i = 0; i < dateList.size(); i++){
                xAxis.add(dateList.get(i).toString()); //Add yAxis entrys and parse toString
            }

            lineDataSets.add(lineDataSet1);
            lineChart.setData(new LineData((ILineDataSet) xAxis,lineDataSet1));

            lineChart.setVisibleXRangeMaximum(61f);

        }

        public void initViews() {        //Todo
            lineChart = (LineChart) findViewById(R.id.lineChart);
            coords =  findViewById(R.id.coordinatorl);
        }

        public void initListeners() {        //todo

        }

        public void initObjects() {      //todo
            bmiDatabaseHelper = new bmiDatabaseHelper(activity);
        }

    }


