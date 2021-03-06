package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Helpers.Equations;
import com.example.myapplication.model.User;
import com.example.myapplication.model.sql.DatabaseHelper;
import com.example.myapplication.model.sql.bmi.measurementsDatabaseHelper;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{


    private BottomNavigationView navigation;
    private final AppCompatActivity activity = HomeActivity.this;
    private ImageView cup1,cup2,cup3,cup4,cup5,cup6,cup7,cup8; //TODO 4 cups instead of 8
    private Button addMeasurements;
    private User user;
    private ImageView[] cups;
    private int iterator = 0;
    private Calendar calendar = Calendar.getInstance();
    private int dayOfTheYear = calendar.get(Calendar.DAY_OF_YEAR);
    private int lastLoginDay;
    private DatabaseHelper databaseHelper;
    private Equations EQ;
    private String email;
    private measurementsDatabaseHelper mHelp;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener //TODO add to all
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
        };




        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        initListeners();
        initObjects();
        Bundle bundle = getIntent().getExtras();
        //todo userName = databaseHelper.getUsername(email);
        //welcomeTextview.setText(TEXT_WELCOME_USER + userName);
        onNewDay();

    }


    private void initViews(){
        navigation = findViewById(R.id.navigation);
        TextView welcomeTextview = findViewById(R.id.welcomeTextview);

        cup1 = findViewById(R.id.cup1);         //Initialise cup images
        cup2 = findViewById(R.id.cup2);
        cup3 = findViewById(R.id.cup3);
        cup4 = findViewById(R.id.cup4);
        cup5 = findViewById(R.id.cup5);
        cup6 = findViewById(R.id.cup6);
        cup7 = findViewById(R.id.cup7);
        cup8 = findViewById(R.id.cup8);

        addMeasurements = findViewById(R.id.add_measurements);
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
        addMeasurements.setOnClickListener(this);

    }

    private void initObjects(){
        user = new User();
        databaseHelper = new DatabaseHelper(activity);
        cups = new ImageView[]{cup1,cup2,cup3,cup4,cup5,cup6,cup7,cup8};
        mHelp = new measurementsDatabaseHelper(activity);
        EQ = new Equations();

    }

   /*private void calcBMR(){
            int age = mHelp.getAge();
            int height = mHelp.getHeight();
            int weight = mHelp.getWeight();         //TODO BMR Calculator
            int activityLevels = mHelp.getActivityLevels();
            boolean maleF = mHelp.getGender();
            EQ.calculateBMR(age,weight,height,activityLevels,maleF);
    }*/


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cup1:
                cupChanger();
                break;
                case R.id.cup2:
                cupChanger();
                break;
                case R.id.cup3:
                cupChanger();
                break;
                case R.id.cup4:
                cupChanger();
                break;
                case R.id.cup5:
                cupChanger();
                break;
                case R.id.cup6:
                cupChanger();
                break;
                case R.id.cup7:
                cupChanger();
                break;
                case R.id.cup8:
                cupChanger();
                break;
            case R.id.add_measurements:
                Intent intent = new Intent(activity, addMeasurements.class);
                startActivity(intent);
                break;

        }

    }

    private void cupChanger(){
        if(iterator < cups.length){
        cups[iterator].setImageResource(R.drawable.fullcup24dp);
        iterator++;}
    }




    private void onNewDay() {                   //Reset screen variables if its a new day
            if(dayOfTheYear > lastLoginDay){      //Todo: Add functionality for it being end of year
                iterator = 0;
                for(int i = 0; i< cups.length; i++){
                    cups[i].setImageResource(R.drawable.emptycup24dp);
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
