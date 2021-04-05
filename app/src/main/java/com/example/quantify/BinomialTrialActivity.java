package com.example.quantify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class BinomialTrialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binomial_trial);
    }

    public void launchQuestionForum(View view) {

        TextView titleId = (TextView)findViewById(R.id.experimentTitleView);
        String expTitle = titleId.getText().toString();

        Intent myIntent = new Intent(BinomialTrialActivity.this, QuestionForumList.class);
        myIntent.putExtra("KEY", expTitle);
        BinomialTrialActivity.this.startActivity(myIntent);
    }

}