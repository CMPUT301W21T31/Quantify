package com.example.quantify;

import android.util.Log;

import java.io.Serializable;
import java.util.Date;

// this class contains all the info we will need to display in the main activity list view
public class Experiment implements Serializable {
    private String desc;
    private String user;
    private String status;
    public Experiment(String desc, String user, String status) {
        this.desc = desc;
        this.user = user;
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
