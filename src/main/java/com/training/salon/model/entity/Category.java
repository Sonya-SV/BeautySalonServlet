package com.training.salon.model.entity;

public class Category {
    private Long id;
    private String name;
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    //Builder
    public static Builder builder() {
        return new Category().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            Category.this.id = id;
            return this;
        }

        public Builder name(String name) {
            Category.this.name = name;
            return this;
        }
        public Builder image(String image) {
            Category.this.image = image;
            return this;
        }
        public Category build() {
            return Category.this;
        }
    }
}
