package com.example.quantify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class QuestionDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_details);

        Intent receiveIntent = getIntent();
        int value = receiveIntent.getIntExtra("qid", 0);
         /*
        TextView text = (TextView) findViewById(R.id.box);
        text.setText("value");
        */


    }
}