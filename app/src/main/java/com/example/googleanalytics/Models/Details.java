package com.example.googleanalytics.Models;

public class Details {
    String title;
    String id;
    String date;
    private  Details(){}
    public Details(String title,String id,String date) {
        this.title = title;
        this.id=id;
        this.date=date;
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
    public String getDate() {
        return date;
    }

}
