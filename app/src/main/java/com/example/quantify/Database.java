package com.example.quantify;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a database class that we can use for our flexibility. All the attributes and methods
 * related to the database stays here. For the parameters of each method, only make those that you
 * need for read/write that particular part from the database so that it remains specific and
 * easier for others to use.
 */
public class Database {


    /**
     * Attributes
     */
    // Question attributes
    private int quesId;
    private String quesText;
    private static final String QUESID = "quesId";
    private static final String QUESTEXT = "quesText";
    private static final int QUESTION = 1;     // for constructor, are the inputs a question

    // reply attributes
    private int replyId;
    private String replyText;
    private static final String REPLYID = "replyId";
    private static final String REPLYTEXT = "replyText";
    private static final int REPLY = 2;        // for constructor, are the inputs a reply


    /**
     * Constructors
     */
    public Database() {
        // do nothing for now
    }

    // Currently this constructor is only for question and answer input.
    public Database(int identify, int id, String message) {

        if (identify == 1) {      // the inputs are a question
            this.quesId = id;
            this.quesText = message;
        } else {     // the inputs are a reply
            this.replyId = id;
            this.replyText = message;
        }
    }


    /**
     * Getters and setters
     */
    public int getQuesId() {
        return quesId;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }

    public String getQuesText() {
        return quesText;
    }

    public void setQuesText(String quesText) {
        this.quesText = quesText;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }


    /**
     * Methods
     */


}


