package com.example.quantify;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TrialIntermediateActivity extends AppCompatActivity {

    Experiment exp;

    TextView expDesc;
    TextView userID;
    TextView minTrials;
    TextView locationText;
    TextView locationView;
    Button start;


    ArrayList<ResultCount> resultCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trial_intermediate);

        Intent intent = getIntent();
        exp = (Experiment) getIntent().getSerializableExtra("Experiment");

        expDesc = findViewById(R.id.experimentDescriptionView);
        userID = findViewById(R.id.userIDView);
        minTrials = findViewById(R.id.MinTrialView);
        locationText = findViewById(R.id.locationText);
        locationView = findViewById(R.id.locationView);
        start = findViewById(R.id.startButton);

        expDesc.setText(exp.getDescription());
        userID.setText(exp.getExperimentID().toString());
        minTrials.setText(exp.getMinTrials().toString());

        if(exp.getLocation().equals("No")){
            locationText.setVisibility(View.INVISIBLE);
            locationView.setVisibility(View.INVISIBLE);
        }
        else{
            // later change this value to be the user's location
            locationView.setText(exp.getLocation());
        }

        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        final CollectionReference collectionReference_1 = db.collection("Experiments");
        final DocumentReference documentReference = collectionReference_1.document(exp.getDescription());
        final CollectionReference collectionReference = documentReference.collection("Trials");

        // create an array of numbers and its counters
        // if the number is unique, add it to array and set count to 1
        // if the number is not unique, increment count
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {


                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    if (doc.getData().get("Trial-Result") != null) {
                        String Trial_id = doc.getId();
                        String Trial_result = (String) doc.getData().get("Trial-Result");
                        Log.d("TAG", Trial_result);

                        // pass the result and its count to next activity smhw

                    }
                }

            }
        });
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