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

import com.example.myapplication.Helpers.Equations;
import com.example.myapplication.Helpers.InputValidation;
import com.example.myapplication.R;
import com.example.myapplication.model.Measurement;
import com.example.myapplication.sql.bmi.bmiDatabaseHelper;

import java.util.Date;

public class FirstTimeUserActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = FirstTimeUserActivity.this;
    private TextView mTextMessage,bmiNumber;
    private EditText textInputAge, textInputHeight, textInputWeight;
    private AppCompatButton bmi;
    private BottomNavigationView navigation;
    private InputValidation inputValidation;
    private Equations equations;
    private int userID;
    private ConstraintLayout constaintLayout;
    private Measurement measurement;
    private bmiDatabaseHelper databaseHelper;


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
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("userId")!=null){
           String value  = bundle.getString("userId");
           userID = Integer.parseInt(value);
        }else{
            Intent Login = new Intent(activity, MainActivity.class);
            startActivity(Login);
        }
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
        inputValidation = new InputValidation(activity);
        equations = new Equations(activity);
        measurement = new Measurement();
        databaseHelper = new bmiDatabaseHelper(activity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bmiButton:
                postDateToSQLite();

                String value = textInputWeight.getText().toString();
                int weight = Integer.parseInt(value);
                String value2 = textInputHeight.getText().toString();
                int height = Integer.parseInt(value2);
                int bmi = equations.calculateBMI(weight, height);
                bmiNumber.setText(bmi);
                break;
        }
}


    private boolean checkInputs(){
        String value = textInputAge.getText().toString().trim();
        if (value.isEmpty()) {
            Snackbar.make(constaintLayout, R.string.ageErrorMessage, Snackbar.LENGTH_LONG).show();
            return false;
        } else {
            String height = textInputHeight.getText().toString().trim();
            if(height.isEmpty()){
                Snackbar.make(constaintLayout, R.string.heightErrorMessage, Snackbar.LENGTH_LONG).show();
                return false;
                }else {
                String weight = textInputWeight.getText().toString().trim();
                if (weight.isEmpty()) {
                    Snackbar.make(constaintLayout, R.string.weightErrorMessage, Snackbar.LENGTH_LONG).show();
                    return false;
                } else return true;
            }
        }
    }

    private void postDateToSQLite(){
        if(checkInputs() == true){
            measurement.setAge(textInputAge.getText().toString().trim());
            measurement.setHeight(textInputHeight.getText().toString().trim());
            measurement.setWeight(textInputWeight.getText().toString().trim());
            measurement.setId(userID);
            measurement.setDate(new Date());
            databaseHelper.addMeasurement(measurement);
            Snackbar.make(constaintLayout, R.string.measurementsAdded, Snackbar.LENGTH_LONG).show();
            Intent intent = new Intent(activity, HomeActivity.class);
            startActivity(intent);
        }
    }

}
