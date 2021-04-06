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

import java.util.UUID;

public class BinomialTrialIntermediateActivity extends AppCompatActivity {

    Experiment exp;

    TextView expDesc;
    TextView userID;
    TextView minTrials;
    TextView locationText;
    TextView locationView;
    TextView SuccessCount;
    TextView FailureCount;
    Button start;

    int SUCCESS;
    int FAILURE;

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
        SuccessCount = findViewById(R.id.SuccessBino);
        FailureCount = findViewById(R.id.FailTextBino);
        start = findViewById(R.id.startButtonBino);



        expDesc.setText(exp.getDescription());
        userID.setText(exp.getExperimentID().toString());
        minTrials.setText(exp.getMinTrials().toString());
        SUCCESS = 0;
        FAILURE = 0;

        // trial array

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
        final DocumentReference documentReference = collectionReference_1.document(id);
        final CollectionReference collectionReference = documentReference.collection("Trials");



        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    String Trial_id = doc.getId();
                    String Trial_result = (String) doc.getData().get("Trial-Result");
                    Log.d("TAG",Trial_result);

                    if (Trial_result.equals("Success")){
                        SUCCESS++;
                        Log.d("TAG","BOOM");
                    }

                    if (Trial_result.equals("Fail")){
                        FAILURE++;
                        Log.d("TAG","BOOM2");
                    }

                }

                SuccessCount.setText(String.valueOf(SUCCESS));
                FailureCount.setText(String.valueOf(FAILURE));
            }
        });



    }

    public void startBinomialTrial(View target){

            Log.d("BLABLA", "Binomial Clicked");
            Intent intent_1 = new Intent(this, BinomialTrialActivity.class);
            intent_1.putExtra("Experiment", exp);
            this.startActivity(intent_1);
    }
}