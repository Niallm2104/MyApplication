package com.example.myapplication.activities;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Helpers.StepDetector;
import com.example.myapplication.Interfaces.StepListener;
import com.example.myapplication.R;
import com.example.myapplication.model.User;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private static final String TEXT_WELCOME_USER = "Hello ";
    private int userID;
    private String userName;
    private BottomNavigationView navigation;
    private final AppCompatActivity activity = HomeActivity.this;
    private ImageView cup1,cup2,cup3,cup4,cup5,cup6,cup7,cup8;
    private TextView welcomeTextview, TvSteps;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private Button BtnStart,BtnStop;
    private int numSteps;
    private User user;
    private ImageView[] cups = {cup1,cup2,cup3,cup4,cup5,cup6,cup7,cup8};
    private int iterator = 0;
    private Calendar calendar = Calendar.getInstance();
    private int dayOfTheYear = calendar.get(Calendar.DAY_OF_YEAR);
    private int lastLoginDay;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_graph:
                    return true;
                case R.id.action_calculators:
                    return true;
                case R.id.action_settings:
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("userId")!=null){
            String value  = bundle.getString("userId");
            userID = Integer.parseInt(value);
        }else{
            Intent Login = new Intent(activity, MainActivity.class);
            startActivity(Login);
        }
        userName = user.getUserName();
        initViews();
        initListeners();
        initObjects();
        welcomeTextview.setText(TEXT_WELCOME_USER + userName);
        onNewDay();

    }


    private void initViews(){
        navigation = findViewById(R.id.navigation);
        welcomeTextview = findViewById(R.id.welcomeTextview);

        cup1 = findViewById(R.id.cup1);         //Initialise cup images
        cup2 = findViewById(R.id.cup2);
        cup3 = findViewById(R.id.cup3);
        cup4 = findViewById(R.id.cup4);
        cup5 = findViewById(R.id.cup5);
        cup6 = findViewById(R.id.cup6);
        cup7 = findViewById(R.id.cup7);
        cup8 = findViewById(R.id.cup8);

        TvSteps = findViewById(R.id.tv_steps);  //Initialise Step counter buttons
        BtnStart = findViewById(R.id.btn_start);
        BtnStop = findViewById(R.id.btn_stop);


    }

    private void initListeners(){
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        cup1.setOnClickListener(this);
        cup2.setOnClickListener(this);
        cup3.setOnClickListener(this);
        cup4.setOnClickListener(this);
        cup5.setOnClickListener(this);
        cup6.setOnClickListener(this);
        cup7.setOnClickListener(this);
        cup8.setOnClickListener(this);
        BtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numSteps = 0;
                sensorManager.registerListener((SensorEventListener) HomeActivity.this,accel,SensorManager.SENSOR_DELAY_FASTEST);
            }
        });
        BtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorManager.unregisterListener((SensorEventListener) HomeActivity.this);
            }
        });

    }

    private void initObjects(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener((StepListener) this);
        user = new User();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cup1:
                cupChanger();            case R.id.cup2:
                cupChanger();            case R.id.cup3:
                cupChanger();            case R.id.cup4:
                cupChanger();            case R.id.cup5:
                cupChanger();            case R.id.cup6:
                cupChanger();            case R.id.cup7:
                cupChanger();            case R.id.cup8:
                cupChanger();        }

    }

    private void cupChanger(){
        cups[iterator].setImageResource(R.drawable.fullCup24dp);
        iterator++;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){
    }

    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1],event.values[2]
            );
        }
    }

    public void step(long timeNs){
        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }


    private void onNewDay() {                   //Reset screen variables if its a new day
            if(dayOfTheYear > lastLoginDay){
                iterator = 0;
                for(int i = 0; i< cups.length; i++){
                    cups[i].setImageResource(R.drawable.emptyCup24dp);
                }
            }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {        //Used to save UI variables to savedInstanceState
        super.onSaveInstanceState(savedInstanceState);                  //This bundle will be passed to onCreate if the process is restarted

        savedInstanceState.putInt("cupIterator", iterator);
                                                                        //Calendar uses ints to store days of the year so simply check if one int is greater than the next
        savedInstanceState.putInt("current_date", dayOfTheYear);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        iterator = savedInstanceState.getInt("cupIterator");
        lastLoginDay = savedInstanceState.getInt("current_date");

    }
}
