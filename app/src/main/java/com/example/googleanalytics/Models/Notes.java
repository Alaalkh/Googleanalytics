package com.example.googleanalytics.Models;

public class Notes {
    String title;
    String id;
    private  Notes(){}
    public Notes(String title,String id) {
        this.title = title;
        this.id=id;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
