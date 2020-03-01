package com.example.artcab.components;

public class Idea {

    private String idea;
    private String user;
    private String time;
    private String userProfile;

    public Idea(String quote, String user, String time, String userImage) {
        this.idea = quote;
        this.user = user;
        this.time = time;
        this.userProfile = userImage;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }
}
