package com.example.quantify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        QuestionDataManagement quesObject = new QuestionDataManagement();

        // ArrayAdapter take the array and sets up the whole list
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quesObject.questions);
        ListView listQuestions = (ListView) findViewById(R.id.questionList);
        listQuestions.setAdapter(listAdapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Intent intent = new Intent(Question.this, QuestionDetails.class);
                intent.putExtra("qid",(int)id);
                startActivity(intent);
            }
        };

        listQuestions.setOnItemClickListener(itemClickListener);

    }

    public void askQuestionButton(View view) {
        Intent intent = new Intent(this, AskQuestion.class);
        startActivity(intent);
    }
}