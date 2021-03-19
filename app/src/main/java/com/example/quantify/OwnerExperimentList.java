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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class OwnerExperimentList extends ArrayAdapter<Experiment> {

    private ArrayList<Experiment> experiments;
    private Context context;



    public OwnerExperimentList(Context context, ArrayList<Experiment> experiments){
        super(context, 0, experiments);
        this.experiments = experiments;
        this.context = context;
    }
    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("Experiments");

        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.owner_card, parent, false);
        }

        Experiment experiment = experiments.get(position);

        TextView expDesc = view.findViewById(R.id.owner_exp_desc);
        TextView expUser = view.findViewById(R.id.owner_exp_user);
        TextView expStatus = view.findViewById(R.id.owner_exp_status);
        Button expEnd = view.findViewById(R.id.button_end);
        Button expDelete = view.findViewById(R.id.button_delete);

        expDesc.setText(experiment.getDescription());
        expUser.setText(experiment.getUser());
        expStatus.setText(experiment.getStatus());




        expDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Experiment exp_to_delete = experiments.get(position);
                experiments.remove(position);
                collectionReference.document(exp_to_delete.getDescription()).delete();
                notifyDataSetChanged();
            }
        });

        expEnd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                experiment.setStatus("End");
                notifyDataSetChanged();
            }
        });





        return view;
    }
}
