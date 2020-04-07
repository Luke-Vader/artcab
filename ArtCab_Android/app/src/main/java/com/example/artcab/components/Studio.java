package com.example.artcab.components;

public class Studio {

    private String name;
    private String location;
    private String carpetArea;
    private String rent;
    private String parking;
    private String postedBy;
    private String deposit;
    private String equipped;
    private String description;
    private String uid;

    public Studio() {}

    public Studio(String name, String location, String carpetArea, String rent, String parking, String postedBy, String deposit, String equipped, String description, String uid) {
        this.name = name;
        this.location = location;
        this.carpetArea = carpetArea;
        this.rent = rent;
        this.parking = parking;
        this.postedBy = postedBy;
        this.deposit = deposit;
        this.equipped = equipped;
        this.description = description;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCarpetArea() {
        return carpetArea;
    }

    public void setCarpetArea(String carpetArea) {
        this.carpetArea = carpetArea;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getEquipped() {
        return equipped;
    }

    public void setEquipped(String equipped) {
        this.equipped = equipped;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
