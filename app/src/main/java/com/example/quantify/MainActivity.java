package com.example.quantify;



import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView experimentList;
    MaterialCardView cardList;
    ArrayAdapter<Experiment> experimentAdapter;
    ArrayList<Experiment> experimentDataList;
    MaterialButton delete_button;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        experimentList = findViewById(R.id.exp_list);

//        delete_button = findViewById(R.id.delete_button);
        experimentDataList = new ArrayList<>();

        experimentDataList.add(new Experiment("HELLO", "USER1", "RUNNING"));
        experimentDataList.add(new Experiment("HELLO", "USER1", "RUNNING"));
        experimentDataList.add(new Experiment("HELLO", "USER1", "RUNNING"));

        experimentAdapter = new ExperimentList(this, experimentDataList);

        experimentList.setAdapter(experimentAdapter);

//        FloatingActionButton fab;
//        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);



        experimentList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Log.d("TAG","BLABLA");
                AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + position);
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        experimentAdapter.remove(experimentAdapter.getItem(positionToRemove));
                        experimentAdapter.notifyDataSetChanged();
                    }});
                adb.show();
            }
        });

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

                        if (item.getTitle() != "Add New"){
                            Log.d("BOOKSHOP", "BOOM");
                        }


                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });//closing the setOnClickListener method






    }


}