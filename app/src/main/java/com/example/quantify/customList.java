package com.example.quantify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

 //this class creates a customlist which connects the fragment layout with the main activity layout
//public class customList extends ArrayAdapter<Experiment> {
//
//    private ArrayList<Experiment> exps;
//    private Context context;
//
//    public customList(Context context, ArrayList<Experiment> exps1){
//        super(context,0, exps1);
//        this.exps = exps1;
//        this.context = context;
//    }
//
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
////        return super.getView(position, convertView, parent);
//        View view = convertView;
//
//        if(view == null){
//            view = LayoutInflater.from(context).inflate(R.layout.card, parent,false);
//        }
//
//        Experiment experiment= exps.get(position);
//
//        TextView expDesc = view.findViewById(R.id.exp_desc);
//        TextView expUser = view.findViewById(R.id.exp_user);
//        TextView expStatus = view.findViewById(R.id.exp_status);
//
//        expDesc.setText("Description: " + experiment.getDesc());
//        expUser.setText("Date: " + experiment.getUser());
//        expStatus.setText("Total Trials: " + experiment.getStatus());
//
//        return view;
//
//    }
//}

public class customList extends RecyclerView.Adapter<customList.MyViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Experiment> exps;
    private ItemClickListener mClickListener;


    public Interface ItemClickListener{
        void onRecyclerViewItemClicked(int position);
    }
    public customList(Context context, ArrayList<Experiment> exps1) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.exps = exps1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.cv.setText(exps.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return exps.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        public CardView cv;

        public MyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card);

            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "You clicked ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
