package com.example.quantify;


import java.io.Serializable;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Experiment exp) {
        return this.description.compareTo(exp.getDescription());
    }
}

