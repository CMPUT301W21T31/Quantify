package com.example.quantify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BinomialTrialActivity extends AppCompatActivity {

    Experiment exp;

    TextView expDesc;
    TextView userID;
    TextView minTrials;
    TextView result;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binomial_trial);

        Intent intent = getIntent();
        exp = (Experiment) getIntent().getSerializableExtra("Experiment");

        expDesc = findViewById(R.id.bTrialDescriptionView);
        userID = findViewById(R.id.bTrialUserIDView);
        minTrials = findViewById(R.id.bMinTrialView);
        result = findViewById(R.id.bResultValue);

        expDesc.setText(exp.getDescription());
        userID.setText(exp.getExperimentID().toString());
        minTrials.setText(exp.getMinTrials().toString());


    }
    public void successClicked(View target){
        result.setText("Success");
    }

    public void failClicked(View target){
        result.setText("Fail");
    }


    public void binomialSaveClicked(View target){
        // we give the trial an ID using UUID and save the result in the database
        Log.d("count", "Result: " + result.getText().toString());
        finish();
    }
}