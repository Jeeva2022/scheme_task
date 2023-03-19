package com.example.scheme.entity;

public class Excep {


    private String message;
    private long timeStamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Excep(String message, long timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public Excep(){}


}
