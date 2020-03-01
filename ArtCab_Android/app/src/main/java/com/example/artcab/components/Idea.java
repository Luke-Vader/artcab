package com.example.artcab.components;

public class Idea {

    private String quote;
    private String user;
    private String time;
    private String userProfile;

    public Idea(String quote, String user, String time, String userImage) {
        this.quote = quote;
        this.user = user;
        this.time = time;
        this.userProfile = userImage;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
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
