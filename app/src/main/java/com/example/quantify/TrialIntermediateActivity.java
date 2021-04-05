package com.example.quantify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TrialIntermediateActivity extends AppCompatActivity {

    Experiment exp;

    TextView expDesc;
    TextView userID;
    TextView minTrials;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trial_intermediate);

        Intent intent = getIntent();
        exp = (Experiment) getIntent().getSerializableExtra("Experiment");

        expDesc = findViewById(R.id.experimentDescriptionView);
        userID = findViewById(R.id.userIDView);
        minTrials = findViewById(R.id.minTrialView);
        start = findViewById(R.id.startButton);

        expDesc.setText(exp.getDescription());
        userID.setText(exp.getExperimentID().toString());
        minTrials.setText(exp.getMinTrials().toString());

    }

    public void startTrial(View target){
        String experiment_type = exp.getType();

        if (experiment_type.equals("Count-based Tests")) {
            Log.d("BLABLA", "Count Clicked");
            Intent intent_1 = new Intent(this, CountTrialActivity.class);
            intent_1.putExtra("Experiment", exp);
            this.startActivity(intent_1);
        } else if (experiment_type.equals("Measurement Trials")) {
            Log.d("BLABLA", "Temperature clicked");
            Intent intent_1 = new Intent(this, MeasurementTrialActivity.class);
            intent_1.putExtra("Experiment", exp);
            this.startActivity(intent_1);
        } else if (experiment_type.equals("Non-negative Integer Counts")) {
            Log.d("BLABLA", "Non-neg clicked");
            Intent intent_1 = new Intent(this, NonNegativeCountTrialActivity.class);
            intent_1.putExtra("Experiment", exp);
            this.startActivity(intent_1);
        }
    }
}