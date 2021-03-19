package com.example.quantify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Question extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Setting up a basic array to try out with local data
        ArrayList<String> questions = new ArrayList<String>();
        questions.add("question 1");
        questions.add("question 2");
        questions.add("question 3");

        // ArrayAdapter take the array and sets up the whole list
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questions);
        ListView listQuestions = (ListView) findViewById(R.id.questionList);
        listQuestions.setAdapter(listAdapter);



    }
}