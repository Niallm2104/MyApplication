package com.example.myapplication.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class WelcomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText weight;
    private EditText height;
    private EditText age;
    private int finalVHeight =0;
    private int finalVAge =0;
    private int finalVWeight =0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);
        getSupportActionBar().hide();

        weight = findViewById(R.id.weight);
        age =  findViewById(R.id.age);
        height = findViewById(R.id.height);
        String valueW = weight.toString();
        finalVWeight = Integer.parseInt(valueW);
        String valueA = age.toString();
        finalVAge = Integer.parseInt(valueA);
        String valueH = height.toString();
        finalVHeight = Integer.parseInt(valueH);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_graph:
                                Snackbar.make(bottomNavigationView, getString(R.string.graph_button), Snackbar.LENGTH_LONG).show();
                                break;
                            case R.id.action_calculators:
                                Snackbar.make(bottomNavigationView, getString(R.string.calculator), Snackbar.LENGTH_LONG).show();
                                break;
                            case R.id.action_settings:
                                Snackbar.make(bottomNavigationView, getString(R.string.action_settings), Snackbar.LENGTH_LONG).show();
                                break;
                        }
                        return false;
                    }
                });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calcbmi:

                int BMi = calculateBMI(this.finalVWeight, this.finalVHeight);
                TextView tv = findViewById(R.id.bmiActual);
                tv.setText(BMi);
        }
    }
        public int calculateBMI(int height, int weight){

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
