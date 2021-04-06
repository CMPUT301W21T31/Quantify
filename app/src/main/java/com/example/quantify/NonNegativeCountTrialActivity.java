package com.example.quantify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NonNegativeCountTrialActivity extends AppCompatActivity {

    Experiment exp;

    TextView expDesc;
    TextView userID;
    TextView minTrials;
    EditText editCount;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_negative_count_trial);

        Intent intent = getIntent();
        exp = (Experiment) getIntent().getSerializableExtra("Experiment");

        expDesc = findViewById(R.id.nTrialDescriptionView);
        userID = findViewById(R.id.nTrialUserIDView);
        minTrials = findViewById(R.id.nMinTrialView);
        editCount = findViewById(R.id.nonNegEdit);

        expDesc.setText(exp.getDescription());
        userID.setText(exp.getExperimentID().toString());
        minTrials.setText(exp.getMinTrials().toString());


    }



    public void nonNegSaveClicked(View target){
        // we give the trial an ID using UUID and save the result in the database
        Log.d("count", "Number: " + editCount.getText().toString());
        finish();
    }
}