package com.example.quantify;

/*
 * This activity is called when user press the button for question in a experiment page. This
 * activity shows a list of questions for that experiment and uses QuestionDataManagement to keep
 * track of its data. Then if user did not find the questions/Answers he was looking for, he can
 * press to ask his/her own question so then we call Ask Question page.
 *
 * If User found a question and is interested in that, they press from the listview and QuestionDetails
 * activity page opens up showing the details for the question and the answers it has.
 */

/*
 * THIS PAGE IS NOT COMPLETE YET.
 */

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

        // OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Intent intent = new Intent(Question.this, QuestionDetails.class);
                intent.putExtra("qid",id);
                startActivity(intent);
            }
        };

        listQuestions.setOnItemClickListener(itemClickListener);

    }

    // the button to ask question
    public void askQuestionButton(View view) {
        Intent intent = new Intent(this, AskQuestion.class);
        startActivity(intent);
    }
}