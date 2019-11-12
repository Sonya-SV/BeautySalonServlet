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
    private boolean isDone;
    private String clientFirstName;
    private String clientLastName;

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }




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
                ", isDone=" + isDone +
                '}';
    }
}
