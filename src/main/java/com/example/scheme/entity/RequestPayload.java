package com.example.scheme.entity;

public class RequestPayload {

    // model class that represents the request json
private Request request;

public static class Request{

    private long schemeId;
    private String filter;
    public long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(long schemeId) {
        this.schemeId = schemeId;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
