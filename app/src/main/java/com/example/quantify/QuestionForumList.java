package com.example.quantify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class QuestionForumList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_forum_list);

        /*
        * FOR LATER IMPLEMENTATION
        * this intent receives the experiment name to create a path in the database
        */
        /*
        Intent intent = getIntent();
        String expTitle = intent.getStringExtra("KEY");
        */

        // Attributes
        List<String> list = new ArrayList<>();
        String path = "/Experiments/Test Trial/Questions";

        //Method Calls
        arrangeListWithDocument(path, list);
        interactWithQuestionList();

    }

    /**
     * interactWithQuestionList(list: List<String>)
     * this method is simple, it contains OnItemClickListener for the QuestionList so that whenever
     * a question is clicked, this method intents to a detail page of that Question info with
     * required information
     */
    public void interactWithQuestionList() {
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(QuestionForumList.this, QuestionDetails.class);
                        startActivity(intent);
                    }
        };

        // Time to connect with the listView from XML
        ListView listView = (ListView)findViewById(R.id.questionList);
        listView.setOnItemClickListener(itemClickListener);


    }

    /**
     * arrangeListWithDocuments(path: String, list: List<String>)
     * This method -
     *      1. connects with the database and reaches the question documents of selected experiment
     *      2. Gets ids of all the documents and store in a array
     *      3. Creates ArrayAdapter to pass the values.
     *      4. Finally, the Questions are displayed in the listview
     *
     * @param path: the absolute path in the database
     * @param list: the array to store all the questions
     */
    public void arrangeListWithDocument(String path, List<String> list) {

        // Firebase connection
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(path).addSnapshotListener(new EventListener<QuerySnapshot>() {

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

                // storing the values
                list.clear();
                for (DocumentSnapshot snapshot : value) {
                    list.add(snapshot.getId());
                }

                // ArrayAdapter
                ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        list
                );

                // Finally! Set the values
                ListView listView = (ListView) findViewById(R.id.questionList);
                listView.setAdapter(listAdapter);
            }


        });
    }

    /**
     * addquestion(view: View)
     * This is a button method, the purpose of this method is to open a new page.
     */
    public void addQuestion(View view) {
        Intent intent = new Intent(this, AddNewQuestion.class);
        startActivity(intent);
    }
}

