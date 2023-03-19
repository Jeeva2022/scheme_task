package com.example.scheme.entity.model;

import java.time.LocalDate;
import java.util.Date;

public class Dataum {

    private String date;
    private String nav;

    public Dataum(){}

    public Dataum(String date,String nav) {
        this.date=date;
        this.nav=nav;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }
}
