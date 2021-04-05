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
        start = findViewById(R.id.startButtonBino);

        expDesc.setText(exp.getDescription());
        userID.setText(exp.getExperimentID().toString());
        minTrials.setText(exp.getMinTrials().toString());
    }

    public void startBinomialTrial(View target){

            Log.d("BLABLA", "Binomial Clicked");
            Intent intent_1 = new Intent(this, BinomialTrialActivity.class);
            intent_1.putExtra("Experiment", exp);
            this.startActivity(intent_1);
    }
}