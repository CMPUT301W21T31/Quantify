package com.example.quantify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ResultList extends ArrayAdapter<Trial> {
    private ArrayList<Trial> trials;
    private Context context;

    public ResultList(Context context, ArrayList<Trial> trials){
        super(context, 0, trials);
        this.trials = trials;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.result_card, parent, false);
        }

        Trial trial = trials.get(position);

        TextView result = view.findViewById(R.id.result);
        TextView userID = view.findViewById(R.id.userID);

        result.setText(trial.getResult());
        userID.setText(trial.getExperimenterID());


        return view;
    }
}
