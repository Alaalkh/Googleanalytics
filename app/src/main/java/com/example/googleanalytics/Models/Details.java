package com.example.googleanalytics.Models;

public class Details {
    String title;
    String id;
    String date;
    String image;
    private  Details(){}
    public Details(String title,String id,String date,String image) {
        this.title = title;
        this.id=id;
        this.date=date;
        this.image=image;
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
    public String getImage() {
        return image;
    }

}
