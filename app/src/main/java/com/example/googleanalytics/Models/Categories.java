package com.example.googleanalytics.Models;

public class Categories {
    String id;
    String categoryname;

    private  Categories(){}
    public Categories(String id, String categoryname) {
        this.id = id;
        this.categoryname = categoryname;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getcategoryname() {
        return categoryname;
    }

}
