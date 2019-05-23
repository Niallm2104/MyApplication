package com.example.myapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.model.Measurement;
import com.example.myapplication.model.sql.bmi.measurementsDatabaseHelper;

public class addMeasurements extends AppCompatActivity implements View.OnClickListener {

    private EditText addWeight;
    private Button addWeightButton, addBfpButton;
    private measurementsDatabaseHelper measurementsDatabaseHelper;
    private final AppCompatActivity activity = addMeasurements.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measurements);

        initViews();
        initObjects();
        initListeners();
    }

    private void initViews() {
        addWeight = findViewById(R.id.add_weight);
        addWeightButton = findViewById(R.id.add_weight_measurement);
        addBfpButton = findViewById(R.id.go_to_bfp);
    }

    private void initObjects() {
        measurementsDatabaseHelper = new measurementsDatabaseHelper(activity);
    }

    private void initListeners() {
        addWeightButton.setOnClickListener(this);
        addBfpButton.setOnClickListener(this);
    }



    private void addWeightMeasurement() {
        Measurement m = new Measurement();
        String w = addWeight.getText().toString().trim();
        float weight = Float.parseFloat(w);
        m.setWeight(weight);
        measurementsDatabaseHelper.addMeasurement(m);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_weight_measurement:
                addWeightMeasurement();
                break;
            case R.id.go_to_bfp:
                //TODO add Intent
                break;
        }
    }
}
