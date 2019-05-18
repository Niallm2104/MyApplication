package com.example.myapplication.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Helpers.Equations;
import com.example.myapplication.R;

public class BFPcalc extends AppCompatActivity {

    private TextView hipText,thighText, abdominalText, tricepText, welcomeText;
    private EditText hipReading, thighReading, abdominalReading, tricepReading, maleFemale, age;
    private boolean male;
    private Button calculate;
    private Equations calculator;
    private double hip,thigh,tricep,abdominal;
    private String BFP;
    private View CoordinatorLayout;
    private BottomNavigationView navigation;
    private AppCompatActivity activity = this;

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener //TODO add to all
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
                    Intent hIntent = new Intent(activity, HomeActivity.class);
                    startActivity(hIntent);
                    return true;
            }
            return false;
        }
    };*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bfpcalc);

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        hipText = findViewById(R.id.HipText);
        thighText = findViewById(R.id.thighText);
        abdominalText = findViewById(R.id.AbdominalText);
        tricepText = findViewById(R.id.tricepText);
        welcomeText = findViewById(R.id.bfpcalctextView);
        calculate = findViewById(R.id.calculateBFP);
        hipReading = findViewById(R.id.HipReading);
        thighReading = findViewById(R.id.thighReading);
        abdominalReading = findViewById(R.id.abdominalReading);
        tricepReading = findViewById(R.id.tricepReading);
        maleFemale = findViewById(R.id.maleFemale);
        age = findViewById(R.id.age);
        CoordinatorLayout = findViewById(R.id.coordinator);
    }

    private void initListeners(){
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputs();
            }
        });
    }

    private void initObjects(){
        calculator = new Equations();
    }

    private void checkInputs(){
        double tricep, hip, thigh, abdominal;
        int ageR;
        String maleFemal;

        try {
            maleFemal = maleFemale.getText().toString();
            tricep = Double.parseDouble(tricepReading.getText().toString());
            hip = Double.parseDouble(hipReading.getText().toString());
            thigh = Double.parseDouble(thighReading.getText().toString());
            abdominal = Double.parseDouble(abdominalReading.getText().toString());
            ageR = Integer.parseInt(age.getText().toString());

            if(maleFemal.toLowerCase() == "male"){
                male = true;
            } else if(maleFemal.toLowerCase() == "female"){
                male = false;
            } else Snackbar.make(CoordinatorLayout, "please enter male/female", Snackbar.LENGTH_LONG);

            BFP = String.valueOf(calculator.calculateBFP(male,tricep,thigh,abdominal,hip, ageR));
        } catch (NumberFormatException exception){
            Snackbar.make(CoordinatorLayout, "One or more of your entrys are incorrectly entered", Snackbar.LENGTH_LONG);
        }
        calculate.setText(BFP);
    }
}
