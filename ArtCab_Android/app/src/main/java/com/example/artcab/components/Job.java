package com.example.artcab.components;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Job {

    String title;
    String organisation;
    String description;
    String postedBy;
    String location;
    private @ServerTimestamp Date timestamp;

    public Job(String title, String organisation, String description, String postedBy, String location, Date timestamp) {
        this.title = title;
        this.organisation = organisation;
        this.description = description;
        this.postedBy = postedBy;
        this.location = location;
        this.timestamp = timestamp;
    }

    public Job() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
