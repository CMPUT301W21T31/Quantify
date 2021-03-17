package com.example.quantify;


import java.io.Serializable;
import java.time.LocalDate;

public class Experiment implements Serializable {
    private String description;
    private String user;
    private String status;


    Experiment(String description, String user, String status){
        this.description = description;
        this.user = user;
        this.status = status;
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
}

