package com.example.quantify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CountTrialActivity extends AppCompatActivity {

    Experiment exp;

    TextView expDesc;
    TextView userID;
    TextView minTrials;
    EditText editCount;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_trial);

        Intent intent = getIntent();
        exp = (Experiment) getIntent().getSerializableExtra("Experiment");

        expDesc = findViewById(R.id.cTrialDescriptionView);
        userID = findViewById(R.id.cTrialUserIDView);
        minTrials = findViewById(R.id.cMinTrialView);
        editCount = findViewById(R.id.countEdit);

        expDesc.setText(exp.getDescription());
        userID.setText(exp.getExperimentID().toString());
        minTrials.setText(exp.getMinTrials().toString());


    }



    public void countSaveClicked(View target){
        // we give the trial an ID using UUID and save the result in the database
        Log.d("count", "Count: " + editCount.getText().toString());
        finish();
    }
}