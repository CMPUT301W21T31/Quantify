package com.example.quantify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QuestionDetails extends AppCompatActivity {

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    private String path;
    private String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_details);

        // getting the values ready
        Intent receiveIntent = getIntent();
        String path = receiveIntent.getStringExtra("PATH_TILL_QUES");
        String question = receiveIntent.getStringExtra("QUESTION");
        String quesDoc = path + "/" + question;
        setPath(quesDoc);
        setQuestion(question);
        List<String> list = new ArrayList<>();

        TextView displayQuestion = (TextView)findViewById(R.id.questionDisplay);
        displayQuestion.setText(getQuestion());


        arrangeListWithDocument(list);
        interactWithQuestionList(list);

    }

    public void askToReply(View view) {


        Toast.makeText(this, "Noted: This Question will get higher priority than others.", Toast.LENGTH_LONG).show();
        view.setEnabled(false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.document(getPath()).update("askedToReplyVote", FieldValue.increment(1));
    }

    /**
     * interactWithQuestionList(list: List<String>)
     * this method is simple, it contains OnItemClickListener for the QuestionList so that whenever
     * a question is clicked, this method intents to a detail page of that Question info with
     * required information
     */
    public void interactWithQuestionList(List<String> list) {
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(QuestionDetails.this, QuesAns.class);
                        startActivity(intent);
                    }
                };

        // Time to connect with the listView from XML
        ListView listView = (ListView)findViewById(R.id.replyList);
        listView.setOnItemClickListener(itemClickListener);

    }


    public void tempFunc(List<String> list) {

    }

    /**
     * arrangeListWithDocuments(path: String, list: List<String>)
     * This method -
     *      1. connects with the database and reaches the question documents of selected experiment
     *      2. Gets ids of all the documents and store in a array
     *      3. Creates ArrayAdapter to pass the values.
     *      4. Finally, the Questions are displayed in the listview
     *
     * @param list: the array to store all the questions
     */
    public void arrangeListWithDocument(List<String> list) {

        // Firebase connection
        String path = getPath() + "/" + "Reply";

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(path)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {

                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        // if the connection have an problem, we don't want the program to crash
                        if (error != null) {

                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Error connecting with Database. Please try again later.",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                            return;
                        }

                        Log.d("IssueQA", "onEvent: Reached Here");
                        // storing the values
                        list.clear();
                        for (DocumentSnapshot snapshot : value) {
                            list.add(snapshot.getId());
                        }

                        if (list.isEmpty()) {
                            TextView textView = (TextView)findViewById(R.id.ReplyAnnounce);
                            textView.setText("No answers available, be the first one to Answer");

                        }

                        Log.d("IssueQA", "onEvent: list: " + list);
                        // ArrayAdapter
                        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                list
                        );

                        // Finally! Set the values
                        ListView listView = (ListView) findViewById(R.id.replyList);
                        listView.setAdapter(listAdapter);
                    }


                });

    }
}