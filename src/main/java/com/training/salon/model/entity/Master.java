package com.training.salon.model.entity;

import java.sql.Blob;
import java.time.LocalTime;

public class Master {
    private Long id;
    private User user;
    private LocalTime timeStart;
    private LocalTime timeEnd;
//    private Blob photo;
//    private byte[] photo;
    private String photo;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

//    public Blob getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(Blob photo) {
//        this.photo = photo;
//    }

//    public byte[] getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(byte[] photo) {
//        this.photo = photo;
//    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
