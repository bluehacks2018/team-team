package com.teamteam.blueboi;

import java.util.ArrayList;

/**
 * Created by Krizia Lynn on 28/01/2018.
 */

public class Request {
    private String uid;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private ArrayList<String> tags;
    private Worker worker;

    public Request(String title, String description, String startDate, String endDate, ArrayList<String> tags, Worker worker) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tags = tags;
        this.worker = worker;
    }

    public Request(){
        this.worker = null;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
