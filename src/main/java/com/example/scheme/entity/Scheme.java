package com.example.scheme.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schemes")
public class Scheme {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "scheme_code")
    private long schemeCode;

    @Column(name = "scheme_name")
    private String schemeName;

    public Scheme() {
    }

    public Scheme(long id, long schemeCode, String schemeName) {
        this.id = id;
        this.schemeCode = schemeCode;
        this.schemeName = schemeName;
    }


    public long getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(long schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
