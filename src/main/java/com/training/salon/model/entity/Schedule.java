package com.training.salon.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private Long id;
    private Master master;
    private User user;
    private Procedure procedure;
    private LocalTime time;
    private LocalDate date;
    private String comment;
    private boolean isDone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", master=" + master +
                ", user=" + user +
                ", procedure=" + procedure +
                ", time=" + time +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}
