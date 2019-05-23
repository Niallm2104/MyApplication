package com.example.myapplication.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.Measurement;
import com.example.myapplication.model.sql.bmi.measurementsDatabaseHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class graphActivity extends AppCompatActivity {

        private LineChart lineChart;
        private final AppCompatActivity activity = graphActivity.this;
        private ArrayList<String> xAxis;
        private ArrayList<Entry> yAxis;
    private ArrayList<Float> yyAxis;
        private List<Measurement> list, dateList;
        private long startDate, endDate;          //TODO should be long
        private String sDate, eDate;
        private CoordinatorLayout coords;
        private String type;
        measurementsDatabaseHelper bmiDatabaseHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_graph1);

            initViews();
            initListeners();
            initObjects();

            Intent intent = getIntent();
            startDate = intent.getLongExtra("sLongCal", 0); //Get start and end date for the calendar
            endDate = intent.getLongExtra("eLongCal", 0);
            sDate = intent.getStringExtra("sCal");
            eDate = intent.getStringExtra("eCal");
            type = intent.getStringExtra("type");

            Snackbar.make(coords, String.valueOf(endDate),Snackbar.LENGTH_LONG).show();
            ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

            LineDataSet lineDataSet1 = new LineDataSet(yAxis, "Weights");
            lineDataSet1.setDrawCircles(false);
            lineDataSet1.setColor(Color.BLUE);
boolean x = true;
           // if(type == "weight") {
                list = bmiDatabaseHelper.getWeightReadings(sDate, eDate);
                dateList = bmiDatabaseHelper.getDateReadings(sDate, eDate, type);
           /* }else if(type == "bfp"){
                list = bmiDatabaseHelper.getbfpReadings(sDate, eDate); //TODO
                dateList = bmiDatabaseHelper.getDateReadings(sDate, eDate, type);
            } else if(type == "bmi"){
                list = bmiDatabaseHelper.getbmiReadings(sDate, eDate);
                dateList = bmiDatabaseHelper.getDateReadings(sDate, eDate, type);
            } else while(x==true){Snackbar.make(coords, "no", Snackbar.LENGTH_INDEFINITE);}
*/
            for(int i = 0; i < list.size(); i++){ //TODO check if this works may have to parse to float
                yyAxis.add(Float.parseFloat(String.valueOf(list.get(i))));
                yAxis.add(new Entry(yyAxis.get(i), i));             //add yAxis entrys from bmi Database
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
            bmiDatabaseHelper = new measurementsDatabaseHelper(activity);
        }

    }


