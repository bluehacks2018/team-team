package com.teamteam.blueboi;

import java.util.ArrayList;

/**
 * Created by Krizia Lynn on 28/01/2018.
 */

public class Worker {
    private String uid;
    private String username;
    private double rating;
    private int totalWork;
    private ArrayList<String> skillset;

    public Worker(String username, double rating, int totalWork, ArrayList<String> skillset) {
        this.username = username;
        this.rating = rating;
        this.totalWork = totalWork;
        this.skillset = skillset;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getTotalWork() {
        return totalWork;
    }

    public void setTotalWork(int totalWork) {
        this.totalWork = totalWork;
    }

    public ArrayList<String> getSkillset() {
        return skillset;
    }

    public void setSkillset(ArrayList<String> skillset) {
        this.skillset = skillset;
    }
}
