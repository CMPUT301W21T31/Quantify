package com.example.quantify;

/*
* This activity launches when user want to post a new question. The purpose for this activity is
* is to generate an automated quesId for the new question, collect the question from Text box and
* then upload the items to the database.
* */

/*
WARNING
THIS IS A UNFINISHED CLASS WHERE THE NEED_UPDATE PART IS MARKED. PLEASE DO NOT FORGET TO
IMPLEMENT THOSE BEFORE REMOVING THIS
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AskQuestion extends AppCompatActivity {

    private int quesId;
    private String questionText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);


    }

    public int getQuesId() {
        return quesId;

    }

    public String getQuestionText() {
        return questionText;
    }


    public void postQuestion(View view) {
        TextView questionDesc = (TextView) findViewById(R.id.questionDesc);
        /*
         * NOT COMPLETE
         * you take the user question,
         * generate automated ID
         * put the info in the database
         * Intent to QuestionList page
         *  */
    }

}
