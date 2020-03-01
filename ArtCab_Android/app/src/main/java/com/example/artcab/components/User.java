package com.example.artcab.components;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.ArrayList;
import java.util.Date;

@IgnoreExtraProperties
public class User {

    private String userId;
    private String name;
    private String email;
    private String instagram;
    private String phone;
    private String whatsapp;
    private String quote;
    private String portfolio;
    private String profileImage;
    private ArrayList<String> links;
    private ArrayList<String> specialisations;
    private ArrayList<String> genres;
    private ArrayList<String> tastes;
    private @ServerTimestamp Date timestamp;

    public User() {

    }

    public User(String userId, String name, String email, String instagram, String phone, String whatsapp, String quote, String portfolio, String profileImage, ArrayList<String> links, ArrayList<String> specialisations, ArrayList<String> genres, ArrayList<String> tastes, Date timestamp) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.instagram = instagram;
        this.phone = phone;
        this.whatsapp = whatsapp;
        this.quote = quote;
        this.portfolio = portfolio;
        this.profileImage = profileImage;
        this.links = links;
        this.specialisations = specialisations;
        this.genres = genres;
        this.tastes = tastes;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<String> links) {
        this.links = links;
    }

    public ArrayList<String> getSpecialisations() {
        return specialisations;
    }

    public void setSpecialisations(ArrayList<String> specialisations) {
        this.specialisations = specialisations;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getTastes() {
        return tastes;
    }

    public void setTastes(ArrayList<String> tastes) {
        this.tastes = tastes;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
