package com.uday.androidsample.model;

public class Country {

    private Facts[] rows;
    private String title;

    public Country(String title, Facts[] rows) {
        this.title = title;
        this.rows = rows;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Facts[] getRows() {
        return rows;
    }

    public void setRows(Facts[] rows) {
        this.rows = rows;
    }

}
