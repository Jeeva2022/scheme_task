package com.example.scheme.entity.model;

import java.util.ArrayList;
import java.util.List;


public class Root {

    private Meta meta;
    private List<Dataum> data;


    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Dataum> getData() {
        return data;
    }

    public void setData(List<Dataum> data) {
        this.data = data;
    }
}
