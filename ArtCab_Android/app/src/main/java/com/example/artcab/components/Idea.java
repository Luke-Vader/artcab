package com.example.artcab.components;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@IgnoreExtraProperties
public class Idea {

    private String idea;
    private String user;
    private String genre;
    private @ServerTimestamp Date timestamp;

    public Idea() { }

    public Idea(String idea, String user, Date timestamp, String genre) {
        this.idea = idea;
        this.user = user;
        this.timestamp = timestamp;
        this.genre = genre;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}