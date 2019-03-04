package com.example.myapplication.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private BottomNavigationView navigation;
    private final AppCompatActivity activity = HomeActivity.this;
    private ImageView cup1,cup2,cup3,cup4,cup5,cup6,cup7,cup8;


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

        initViews();
        initListeners();
        initObjects();

    }

    private void initViews(){
        navigation = findViewById(R.id.navigation);

        cup1 = findViewById(R.id.cup1);         //Initialise cup images
        cup2 = findViewById(R.id.cup2);
        cup3 = findViewById(R.id.cup3);
        cup4 = findViewById(R.id.cup4);
        cup5 = findViewById(R.id.cup5);
        cup6 = findViewById(R.id.cup6);
        cup7 = findViewById(R.id.cup7);
        cup8 = findViewById(R.id.cup8);
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
    }

    private void initObjects(){

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cup1:
                cup1.setImageResource(R.drawable.fullCup24dp);
            case R.id.cup2:
                cup2.setImageResource(R.drawable.fullCup24dp);
            case R.id.cup3:
                cup3.setImageResource(R.drawable.fullCup24dp);
            case R.id.cup4:
                cup4.setImageResource(R.drawable.fullCup24dp);
            case R.id.cup5:
                cup5.setImageResource(R.drawable.fullCup24dp);
            case R.id.cup6:
                cup6.setImageResource(R.drawable.fullCup24dp);
            case R.id.cup7:
                cup7.setImageResource(R.drawable.fullCup24dp);
            case R.id.cup8:
                cup8.setImageResource(R.drawable.fullCup24dp);
        }

    }
}
