package com.example.quantify;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ExperimenterExperimentList extends ArrayAdapter<Experiment> {

    private ArrayList<Experiment> experiments;
    private ArrayList<Experiment> subscribed;
    private Context context;

    public ExperimenterExperimentList(Context context, ArrayList<Experiment> experiments, ArrayList<Experiment> subscribed){
        super(context, 0, experiments);
        this.experiments = experiments;
        this.subscribed = subscribed;
        this.context = context;
    }
    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.experimenter_card, parent, false);
        }

        Experiment experiment = experiments.get(position);

        LinearLayout card = view.findViewById(R.id.exp_card);

        TextView expDesc = view.findViewById(R.id.experimenter_exp_desc);
        TextView expUser = view.findViewById(R.id.experimenter_exp_user);
        TextView expStatus = view.findViewById(R.id.experimenter_exp_status);

        expDesc.setText(experiment.getDescription());
        expUser.setText(experiment.getUser());
        expStatus.setText(experiment.getStatus());

        card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Log.d("BLABLA",subscribed.toString());
                if(!(context instanceof SubscribedActivity)){
                    Toast.makeText(context ,"Subscribed", Toast.LENGTH_SHORT).show();
                }

                if(!subscribed.contains(experiments.get(position))) {
                    subscribed.add(experiments.get(position));
                }
                notifyDataSetChanged();
                return false;
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String experiment_type = (String) getItem(position).getType();

//                Toast.makeText(context ,(String) getItem(position).getLocation(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(context ,(String) getItem(position).getDescription(), Toast.LENGTH_SHORT).show();
                if ( ((String) getItem(position).getLocation()).equals("Yes") ) {
                    //Creating the instance of PopupMenu
                    View view_1 = LayoutInflater.from(context).inflate(R.layout.location_warning, null);


                    AlertDialog.Builder adb = new AlertDialog.Builder(context);
                    adb.setTitle("WARNING!!");
                    adb.setMessage("Proceed with caution!");
                    adb.setView(view_1);
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Proceed", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (experiment_type.equals("Binomial Trials")) {
                                Log.d("BLABLA", "Binomial Clicked");
                                Intent intent_1 = new Intent(context, BinomialTrialActivity.class);
                                intent_1.putExtra("typename", getItem(position));
                                context.startActivity(intent_1);
                            } else if (experiment_type.equals("Count-based Tests")) {
                                Log.d("BLABLA", "Count Clicked");
                                Intent intent_1 = new Intent(context, CountTrialActivity.class);
                                intent_1.putExtra("typename", getItem(position));
                                context.startActivity(intent_1);
                            } else if (experiment_type.equals("Measurement Trials")) {
                                Log.d("BLABLA", "Temperature clicked");
                                Intent intent_1 = new Intent(context, MeasurementTrialActivity.class);
                                intent_1.putExtra("typename", getItem(position));
                                context.startActivity(intent_1);
                            } else if (experiment_type.equals("Non-negative Integer Counts")) {
                                Log.d("BLABLA", "Non-neg clicked");
                                Intent intent_1 = new Intent(context, NonNegativeCountTrialActivity.class);
                                intent_1.putExtra("typename", getItem(position));
                                context.startActivity(intent_1);
                            }
                        }
                    });
                    adb.show();
                }
                else{
                    if (experiment_type.equals("Binomial Trials")) {
                        Log.d("BLABLA", "Binomial Clicked");
                        Intent intent_1 = new Intent(context, BinomialTrialActivity.class);
                        intent_1.putExtra("typename", getItem(position));
                        context.startActivity(intent_1);
                    } else if (experiment_type.equals("Count-based Tests")) {
                        Log.d("BLABLA", "Count Clicked");
                        Intent intent_1 = new Intent(context, CountTrialActivity.class);
                        intent_1.putExtra("typename", getItem(position));
                        context.startActivity(intent_1);
                    } else if (experiment_type.equals("Measurement Trials")) {
                        Log.d("BLABLA", "Temperature clicked");
                        Intent intent_1 = new Intent(context, MeasurementTrialActivity.class);
                        intent_1.putExtra("typename", getItem(position));
                        context.startActivity(intent_1);
                    } else if (experiment_type.equals("Non-negative Integer Counts")) {
                        Log.d("BLABLA", "Non-neg clicked");
                        Intent intent_1 = new Intent(context, NonNegativeCountTrialActivity.class);
                        intent_1.putExtra("typename", getItem(position));
                        context.startActivity(intent_1);
                    }
                }
            }

        });

        return view;
    }
}
