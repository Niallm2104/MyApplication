package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class graphDates extends AppCompatActivity implements View.OnClickListener {

    private EditText sDate;
    private EditText eDate;
    private TextView hi;
    private Button inputDates, back;
    private long startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_dates);

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        sDate = findViewById(R.id.startDate);
        eDate = findViewById(R.id.eDate);
        hi = findViewById(R.id.hi);
        inputDates = findViewById(R.id.display_graph);
        back = findViewById(R.id.back);
    }
    private void initObjects(){

    }
    private void initListeners(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.display_graph:
                startDate = Long.parseLong(sDate.getText().toString());
        }
    }
}
