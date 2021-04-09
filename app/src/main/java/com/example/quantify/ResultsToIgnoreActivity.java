package com.example.quantify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultsToIgnoreActivity extends AppCompatActivity {

    ListView trialList;
    ArrayAdapter<Trial> trialAdapter;
    ArrayList<Trial> trialDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        trialList = findViewById(R.id.result_list);
        trialDataList = new ArrayList<>();
        trialAdapter = new ResultList(ResultsToIgnoreActivity.this, trialDataList);

        trialList.setAdapter(trialAdapter);
    }
}