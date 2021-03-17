package com.example.quantify;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView experimentList;
    MaterialCardView cardList;
    ArrayAdapter<Experiment> ownerExperimentAdapter;
    ArrayAdapter<Experiment> experimenterExperimentAdapter;
    ArrayList<Experiment> ownerExperimentDataList;
    ArrayList<Experiment> experimenterExperimentDataList;

    MaterialButton delete_button;
    EditText expDesc;
    EditText expUser;
    EditText expStatus;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        experimentList = findViewById(R.id.exp_list);

        //        delete_button = findViewById(R.id.delete_button);
        ownerExperimentDataList = new ArrayList<>();
        experimenterExperimentDataList = new ArrayList<>();


        ownerExperimentDataList.add(new Experiment("HELLO", "USER1", "RUNNING"));
        ownerExperimentDataList.add(new Experiment("HELLO", "USER1", "RUNNING"));
        ownerExperimentDataList.add(new Experiment("HELLO", "USER1", "RUNNING"));

        experimenterExperimentDataList.add(new Experiment("HELLO", "USER1", "RUNNING"));
        experimenterExperimentDataList.add(new Experiment("HELLO", "USER1", "RUNNING"));
        experimenterExperimentDataList.add(new Experiment("HELLO", "USER1", "RUNNING"));


        ownerExperimentAdapter = new OwnerExperimentList(MainActivity.this, ownerExperimentDataList);
        experimenterExperimentAdapter = new ExperimenterExperimentList(MainActivity.this, experimenterExperimentDataList);

        // initially, we see the owner view
        experimentList.setAdapter(ownerExperimentAdapter);


//        FloatingActionButton fab;
//        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);


        Toolbar topAppBar;
        topAppBar = (Toolbar) findViewById(R.id.topAppBar);
//        setSupportActionBar(topAppBar);
//
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the navigation icon press

            }
        });
//
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.search:
                        // Handle search icon press

                    case R.id.user:
                        // Handle user icon press

                    case R.id.more:
                        // Handle more icon press

                }
                return false;
            }
        });

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity.this,"You Clicked : " + tab.getPosition(), Toast.LENGTH_SHORT).show();

                if( tab.getPosition() == 0){
                    experimentList.setAdapter(ownerExperimentAdapter);
                    ownerExperimentAdapter.notifyDataSetChanged();
                }
                else{
                    experimentList.setAdapter(experimenterExperimentAdapter);
                    experimenterExperimentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Handle tab reselect
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Handle tab unselect
            }

        });
//
        FloatingActionButton fab;
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, fab);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        Log.d("BLABLA",(String) item.getTitle());

                         if(((String) item.getTitle()).equals("Add New")){
                             View view_1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_experiment_fragment_layout, null);
                             expDesc = view_1.findViewById(R.id.exp_desc_fragment);
                             expUser = view_1.findViewById(R.id.exp_user_fragment);
                             expStatus = view_1.findViewById(R.id.exp_status_fragment);


                             AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
                             adb.setTitle("Add?");
                             adb.setMessage("Are you sure you want to Add Experiment");
                             adb.setView(view_1);
                             adb.setNegativeButton("Cancel", null);
                             adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                                 public void onClick(DialogInterface dialog, int which) {
                                     String exp_description = expDesc.getText().toString();
                                     String exp_username = expUser.getText().toString();
                                     String exp_status = expStatus.getText().toString();

                                     ownerExperimentDataList.add(new Experiment(exp_description, exp_username, exp_status));
                                     experimenterExperimentDataList.add(new Experiment(exp_description, exp_username, exp_status));

//                                     current_exp.setExp_desc(exp_description);
//                                     current_exp.setUser(exp_username);
//                                     current_exp.setStatus(exp_status);
//
//
//
//
//                        Experiment mycity = new Experiment(exp_name, exp_description, null,S_Total,F_Total);
//                        experimentAdapter.remove(experimentAdapter.getItem(positionToRemove));
//                        experimentAdapter.insert(mycity, positionToRemove);
                                     ownerExperimentAdapter.notifyDataSetChanged();


                                 }});
                             adb.show();

                         }


                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });//closing the setOnClickListener method






    }


}