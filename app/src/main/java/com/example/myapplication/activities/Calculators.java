package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class Calculators extends AppCompatActivity {

    private Button bfp;
    private Button bmi;
    private AppCompatActivity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculators);
        initViews();
        initListeners();
    }

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
                    return true;
            }
            return false;
        }
    };

    private void initViews() {
        bfp = findViewById(R.id.BFPbutton);
        bmi = findViewById(R.id.BMI);
        TextView textView = findViewById(R.id.pleaseChoose);
    }

    private void initListeners(){
        bfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, BFPcalc.class);
                startActivity(intent);
            }
        });
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(activity, BMIcalc.class);
              //  startActivity(intent);
            }
        });
    }
}
