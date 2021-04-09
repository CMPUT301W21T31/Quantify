package com.example.quantify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ResultsToIgnoreActivity extends AppCompatActivity {

    Experiment exp;

    ListView trialList;
    ArrayAdapter<Trial> trialAdapter;
    ArrayList<Trial> trialDataList;

    TextView exp_name;
    TextView userID;
    TextView status;

    ArrayList<String> Trial_list;
    ArrayList<Integer> Count_list;

    ArrayList<String> result_date_list;
    ArrayList<Integer> result_count_list;

    int success;
    int failure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Intent intent = getIntent();
        exp = (Experiment) intent.getSerializableExtra("Experiment");

        exp_name = findViewById(R.id.experiment_name);
        userID = findViewById(R.id.userIDView);
        status = findViewById(R.id.exp_status);

        exp_name.setText(exp.getDescription());
        userID.setText(exp.getUser());
        status.setText(exp.getStatus());

        trialList = findViewById(R.id.result_list);
        trialDataList = new ArrayList<>();
        trialAdapter = new ResultList(ResultsToIgnoreActivity.this, trialDataList);

        trialList.setAdapter(trialAdapter);

        Trial_list = new ArrayList<String>();
        Count_list = new ArrayList<Integer>();

        result_date_list = new ArrayList<>();
        result_count_list = new ArrayList<>();

        success = 0;
        failure = 0;


        String exp_name = exp.getDescription();

        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference_1 = db.collection("Experiments");
        final DocumentReference documentReference = collectionReference_1.document(exp_name);
        final CollectionReference collectionReference = documentReference.collection("Trials");

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                trialDataList.clear();
                Trial_list.clear();
                Count_list.clear();
                result_date_list.clear();
                result_count_list.clear();
                success = 0;
                failure = 0;


                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    String experimenter_id = (String) doc.getData().get("Experimenter ID");
                    String Trial_date = "0";
                    try {
                         Trial_date = (String) doc.getData().get("Trial Date");
                    }
                    catch(Exception e){
                    }
                    String Trial_result = (String) doc.getData().get("Trial-Result");
                    Trial new_trial = new Trial(experimenter_id, Trial_date, Trial_result);
                    trialDataList.add(new_trial);

                    if (Trial_result.equals("Success")) {
                        success++;
                        Log.d("TAG", "BOOM");
                    }

                    if (Trial_result.equals("Fail")) {
                        failure++;
                        Log.d("TAG", "BOOM2");
                    }
                }
                trialAdapter.notifyDataSetChanged();

                for(int counter = 0; counter < trialDataList.size(); counter++){
                    String Trial_result = trialDataList.get(counter).getResult();
                    String Result_date = trialDataList.get(counter).getDate();
                    if (Trial_list.contains(Trial_result)){
                        int index = Trial_list.indexOf(Trial_result);
                        Count_list.set(index, Count_list.get(index) + 1);
                    }
                    else {
                        Trial_list.add(Trial_result);
                        Count_list.add(1);
                    }

                    if (result_date_list.contains(Result_date)) {
                        int index = result_date_list.indexOf(Result_date);
                        result_count_list.set(index, result_count_list.get(index) + 1);
                    } else {
                        result_date_list.add(Result_date);
                        result_count_list.add(1);
                    }
                }
            }
        });

    }

    public void createMyHistogram(View target){

        if(Count_list.size() > 0 && Trial_list.size() > 0) {
            if (exp.getType().equals("Binomial Trials")) {
                Intent intent_1 = new Intent(this, BinomialHistogramActivity.class);
                intent_1.putExtra("success", success);
                intent_1.putExtra("fail", failure);
                this.startActivity(intent_1);
            } else {
                Intent intent_1 = new Intent(this, OtherHistogramActivity.class);
                intent_1.putExtra("y-axis", Count_list);
                intent_1.putExtra("x-axis", Trial_list);
                this.startActivity(intent_1);
            }
        }
        else{
            Toast.makeText(this, "No trials available", Toast.LENGTH_SHORT).show();
        }

    }

    public void myResultsOverTimeOtherClicked(View target){
        Log.d("BLABLA", "Results Clicked");
        Intent intent_1 = new Intent(this, ResultsOverTimeActivity.class);
        intent_1.putExtra("y-axis", result_count_list);
        intent_1.putExtra("x-axis", result_date_list);
        this.startActivity(intent_1);
    }
}