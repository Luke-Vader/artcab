package com.example.artcab.components;

import com.google.firebase.firestore.DocumentReference;

public class Post {

    private String uid;
    private String title;
    private DocumentReference object;
    private String type;

    public Post() { }

    public Post(String uid, String title, DocumentReference object, String type) {
        this.uid = uid;
        this.title = title;
        this.object = object;
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DocumentReference getObject() {
        return object;
    }

    public void setObject(DocumentReference object) {
        this.object = object;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
