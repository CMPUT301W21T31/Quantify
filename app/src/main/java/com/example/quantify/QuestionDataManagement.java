package com.example.quantify;

import java.util.ArrayList;

public class QuestionDataManagement {
    private int qid;
    private String question;
    private int aid;
    private String answer;
    public ArrayList<String> questions;

    public QuestionDataManagement() {
        questions = new ArrayList<>();
        questions.add("question 1");
        questions.add("question 2");
        questions.add("question 3");
        questions.add("question 4");
    }


}
