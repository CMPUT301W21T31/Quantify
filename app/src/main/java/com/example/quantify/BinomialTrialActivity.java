package com.example.quantify;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

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
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        final CollectionReference collectionReference_1 = db.collection("Experiments");
        final DocumentReference documentReference = collectionReference_1.document(id);
        final CollectionReference collectionReference = documentReference.collection("Trials");

        HashMap<String, String> data = new HashMap<>();
        data.put("Trial-Result", result.getText().toString());
        UUID Trial_id = UUID.randomUUID();

        collectionReference
                .document(Trial_id.toString())
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // These are a method which gets executed when the task is succeeded
                        Log.d("TAG", "Data has been added successfully!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // These are a method which gets executed if there’s any problem
                        Log.d("TAG", "Data could not be added!" + e.toString());
                    }
                });


        Log.d("count", "Count: " + result.getText().toString());
        finish();
    }
}