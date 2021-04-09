package com.example.quantify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ResultsToIgnoreActivity extends AppCompatActivity {

    ListView trialList;
    ArrayAdapter<Trial> trialAdapter;
    ArrayList<Trial> trialDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        trialList = findViewById(R.id.result_list);
        trialDataList = new ArrayList<>();
        trialAdapter = new ResultList(ResultsToIgnoreActivity.this, trialDataList);

        trialList.setAdapter(trialAdapter);

        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        Experiment exp = (Experiment) intent.getSerializableExtra("Experiment");
        String exp_name = exp.getDescription();
        final CollectionReference collectionReference_1 = db.collection("Experiments");
        final DocumentReference documentReference = collectionReference_1.document(exp_name);
        final CollectionReference collectionReference = documentReference.collection("Trials");

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                trialDataList.clear();
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    String experimenter_id = (String) doc.getData().get("Experimenter ID");
                    String Trial_date = (String) doc.getData().get("Trial Date");
                    String Trial_result = (String) doc.getData().get("Trial-Result");
                    Trial new_trial = new Trial(experimenter_id, Trial_date, Trial_result);
                    trialDataList.add(new_trial);
                }
                trialAdapter.notifyDataSetChanged();
            }
        });

















    }
}