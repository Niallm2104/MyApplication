package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Helpers.Equations;
import com.example.myapplication.model.Helpers.InputValidation;
import com.example.myapplication.model.Measurement;
import com.example.myapplication.model.sql.bmi.measurementsDatabaseHelper;

public class FirstTimeUserActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = FirstTimeUserActivity.this;
    private TextView mTextMessage,bmiNumber;
    private EditText textInputAge, textInputHeight, textInputWeight;
    private AppCompatButton bmi;
    private BottomNavigationView navigation;
    private Equations equations;
    private ConstraintLayout constaintLayout;
    private float value = 0, value1 = 0, value2 = 2;
    measurementsDatabaseHelper databaseHelper;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_graph:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.action_calculators:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.action_settings:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_user);
        Bundle bundle = getIntent().getExtras();        //TODO fix this
       // if(bundle.getString("userId")!=null){
       //    String value  = bundle.getString("userId");
        //   userID = Integer.parseInt(value);
       // }else{
       //     Intent Login = new Intent(activity, MainActivity.class);
       //     startActivity(Login);
        //}
        initViews();
        initListeners();
        initObjects();

    }

    private void initViews(){

        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        textInputAge = findViewById(R.id.Age);
        textInputHeight = findViewById(R.id.height);
        textInputWeight = findViewById(R.id.weight);
        bmi = findViewById(R.id.bmiButton);
        bmiNumber = findViewById(R.id.bmi);

    }

    private void initListeners(){
        bmi.setOnClickListener(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void initObjects(){
        InputValidation inputValidation = new InputValidation(activity);
        equations = new Equations();
        databaseHelper = new measurementsDatabaseHelper(activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bmiButton:
                postDateToSQLite(); //Todo fix
                String value = textInputWeight.getText().toString().trim();
                float weight = Integer.parseInt(value);
                String value2 = textInputHeight.getText().toString().trim();
                float height = Integer.parseInt(value2);
                float bmi = equations.calculateBMI(weight, height);
                String fBmi = String.valueOf(bmi);
                bmiNumber.setText(fBmi);
                break;
        }
}


    private boolean checkInputs(){
        try{String et = textInputAge.getText().toString().trim();
            value = Float.parseFloat(et);}
        catch(NumberFormatException exception){
            Snackbar.make(constaintLayout, R.string.ageErrorMessage, Snackbar.LENGTH_LONG).show();
        }
        if (value == 0) {
            Snackbar.make(constaintLayout, R.string.ageErrorMessage, Snackbar.LENGTH_LONG).show();
            return false;
        } else {
            System.out.println("age ok");
            try{String height = textInputHeight.getText().toString().trim();
                value1 = Float.parseFloat(height);}
                catch(NumberFormatException exception){
                    Snackbar.make(constaintLayout, R.string.ageErrorMessage, Snackbar.LENGTH_LONG).show();
                }
            if(value1 == 0){
                Snackbar.make(constaintLayout, R.string.heightErrorMessage, Snackbar.LENGTH_LONG).show();
                return false;
                }else {
                System.out.println("height ok");
                try{String weight = textInputWeight.getText().toString().trim();
                    value2 = Float.parseFloat(weight);}
                    catch(NumberFormatException exception){
                        Snackbar.make(constaintLayout, R.string.ageErrorMessage, Snackbar.LENGTH_LONG).show();
                    }
                if (value2 == 0) {
                    Snackbar.make(constaintLayout, R.string.weightErrorMessage, Snackbar.LENGTH_LONG).show();
                    return false;
                } else return true;
            }
        }
    }

    private void postDateToSQLite(){
        if(checkInputs()){
            System.out.println("check inputs working");
            Measurement measurement = new Measurement();
            measurement.setAge((int) value);
            measurement.setHeight((int) value1);
            measurement.setWeight((int) value2);
            databaseHelper.addMeasurement(measurement);
            Intent intent = new Intent(activity, HomeActivity.class);
            startActivity(intent);
        }
    }

}
