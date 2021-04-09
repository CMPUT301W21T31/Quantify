package com.example.quantify;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

public class NonNegativeCountTrialActivity extends AppCompatActivity {

    Experiment exp;

    String longitude;
    String latitude;

    TextView expDesc;
    TextView userID;
    TextView minTrials;
    EditText editCount;

    Date date;
    SimpleDateFormat currentDate;
    String formattedCurrentDate;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_negative_count_trial);

        Intent intent = getIntent();
        exp = (Experiment) getIntent().getSerializableExtra("Experiment");
        longitude = getIntent().getStringExtra("Longitude");
        latitude = getIntent().getStringExtra("Latitude");

        expDesc = findViewById(R.id.nTrialDescriptionView);
        userID = findViewById(R.id.nTrialUserIDView);
        minTrials = findViewById(R.id.nMinTrialView);
        editCount = findViewById(R.id.nonNegEdit);

        date = Calendar.getInstance().getTime();
        currentDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        formattedCurrentDate = currentDate.format(date);

        expDesc.setText(exp.getDescription());
        userID.setText(exp.getExperimentID().toString());
        minTrials.setText(exp.getMinTrials().toString());


    }



    public void nonNegSaveClicked(View target) {
        // we give the trial an ID using UUID and save the result in the database
        Log.d("count", "Number: " + editCount.getText().toString());

        if(!editCount.getText().toString().equals("-")){
            if (!(editCount.getText().toString().equals("")) && Integer.parseInt(editCount.getText().toString()) >= 0) {
                FirebaseFirestore db;
                db = FirebaseFirestore.getInstance();
                String id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                final CollectionReference collectionReference_1 = db.collection("Experiments");
                final DocumentReference documentReference = collectionReference_1.document(exp.getDescription());
                final CollectionReference collectionReference = documentReference.collection("Trials");

                HashMap<String, String> data = new HashMap<>();
                data.put("Trial-Result", editCount.getText().toString());
                data.put("Experimenter ID", Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
                data.put("Trial Date", formattedCurrentDate);
                data.put("Location Latitude", latitude);
                data.put("Location Longitude", longitude);
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


                Log.d("count", "Count: " + editCount.getText().toString());
            } else {
                Toast.makeText(NonNegativeCountTrialActivity.this, "Please insert a non-negative number. Could not create trial.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(NonNegativeCountTrialActivity.this, "Please insert a non-negative number. Could not create trial.", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}