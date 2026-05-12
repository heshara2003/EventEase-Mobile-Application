package com.example.eventease;

import java.io.Serializable;

public class Event implements Serializable {
    private String id;
    private String title;
    private String date;
    private String time;
    private String location;
    private String status;

    public Event(String id, String title, String date, String time, String location, String status) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}