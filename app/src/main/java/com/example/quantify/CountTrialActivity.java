package com.example.quantify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CountTrialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_trial);


    }

    public void OpenQuestionList(View view) {
        Intent intent = new Intent(this, Question.class);
        startActivity(intent);
    }
}