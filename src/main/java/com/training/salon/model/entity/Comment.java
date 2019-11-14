package com.training.salon.model.entity;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private Master master;
    private User user;
    private String comment;
    private LocalDateTime dateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", master=" + master +
                ", user=" + user +
                ", comment='" + comment + '\'' +
                '}';
    }
    //Builder
    public static Builder builder() {
        return new Comment().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            Comment.this.id = id;
            return this;
        }

        public Builder master(Master master) {
            Comment.this.master = master;
            return this;
        }
        public Builder user(User user) {
            Comment.this.user = user;
            return this;
        }
        public Builder comment(String comment) {
            Comment.this.comment = comment;
            return this;
        }

        public Builder dateTime(LocalDateTime dateTime) {
            Comment.this.dateTime = dateTime;
            return this;
        }

        public Comment build() {
            return Comment.this;
        }
    }
}
