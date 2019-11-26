package com.training.salon.model.entity;

import java.time.LocalTime;

public class Master {
    private Long id;
    private User user;
    private Category category;
    private LocalTime timeStart;
    private LocalTime timeEnd;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Master{" +
                "id=" + id +
                ", user=" + user +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }

    public static Builder builder() {
        return new Master().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            Master.this.id = id;
            return this;
        }

        public Builder user(User user) {
            Master.this.user = user;
            return this;
        }

        public Builder category(Category category) {
            Master.this.category = category;
            return this;
        }

        public Builder timeStart(LocalTime timeStart) {
            Master.this.timeStart = timeStart;
            return this;
        }

        public Builder timeEnd(LocalTime timeEnd) {
            Master.this.timeEnd = timeEnd;
            return this;
        }

        public Builder photo(String photo) {
            Master.this.photo = photo;
            return this;
        }

        public Master build() {
            return Master.this;
        }
    }
}
