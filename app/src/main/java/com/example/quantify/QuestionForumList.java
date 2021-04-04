package com.example.quantify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class QuestionForumList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_forum_list);

        Intent intent = getIntent();
        String expTitle = intent.getStringExtra("KEY");

        ListView questionList = (ListView) findViewById(R.id.list_questions);
    }


    //public void createListView()

    /**
     * questionForumFillUP()
     * This method takes the experiment id, finds all the questions of that experiment
     * and arrange in a list for user to interact with.
     *
     * @param expId: int - experiment Id
     */
    public void questionForumFillUp(int expId) {


    }
}