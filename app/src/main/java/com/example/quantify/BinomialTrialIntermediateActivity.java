package com.example.quantify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BinomialTrialIntermediateActivity extends AppCompatActivity {

    Experiment exp;

    TextView expDesc;
    TextView userID;
    TextView minTrials;
    TextView locationText;
    TextView locationView;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binomial_intermediate);

        Intent intent = getIntent();
        exp = (Experiment) getIntent().getSerializableExtra("Experiment");

        expDesc = findViewById(R.id.experimentDescriptionViewBino);
        userID = findViewById(R.id.userIDViewBino);
        minTrials = findViewById(R.id.minTrialViewBino);
        locationText = findViewById(R.id.locationTextBino);
        locationView = findViewById(R.id.locationViewBino);
        start = findViewById(R.id.startButtonBino);

        expDesc.setText(exp.getDescription());
        userID.setText(exp.getExperimentID().toString());
        minTrials.setText(exp.getMinTrials().toString());

        // trial array

        if(exp.getLocation().equals("No")){
            locationText.setVisibility(View.INVISIBLE);
            locationView.setVisibility(View.INVISIBLE);
        }
        else{
            // later change this value to be the user's location
            locationView.setText(exp.getLocation());
        }
    }

    public void startBinomialTrial(View target){

            Log.d("BLABLA", "Binomial Clicked");
            Intent intent_1 = new Intent(this, BinomialTrialActivity.class);
            intent_1.putExtra("Experiment", exp);
            this.startActivity(intent_1);
    }
}