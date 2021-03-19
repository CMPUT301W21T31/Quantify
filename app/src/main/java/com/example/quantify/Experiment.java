package com.example.quantify;


import java.io.Serializable;

/**
 * This is a class that has all the information about Experiments
 */

public class Experiment implements Serializable, Comparable<Experiment>{
    private String description;
    private String user;
    private String status;
    private String type;


    Experiment(String description, String user, String status, String type){
        this.description = description;
        this.user = user;
        this.status = status;
        this.type = type;
    }

    /**
     * This gets the experiment description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This sets the experiment description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This gets the experiment user
     * @return description
     */
    public String getUser() {
        return user;
    }

    /**
     * This sets the experiment user
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * This gets the experiment status
     * @return description
     */
    public String getStatus() {
        return status;
    }

    /**
     * This sets the experiment status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This gets the experiment type
     * @return description
     */
    public String getType() {
        return type;
    }

    /**
     * This sets the experiment type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    /**
     * This compares the descriptions of 2 Experiments
     * @param exp
     * @return an integer
     */
    public int compareTo(Experiment exp) {
        return this.description.compareTo(exp.getDescription());
    }
}

