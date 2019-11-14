package com.training.salon.model.entity;

import java.math.BigDecimal;

public class Procedure {
    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    public static Builder builder() {
        return new Procedure().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            Procedure.this.id = id;
            return this;
        }

        public Builder name(String name) {
            Procedure.this.name = name;
            return this;
        }

        public Builder price(BigDecimal price) {
            Procedure.this.price = price;
            return this;
        }

        public Builder category(Category category) {
            Procedure.this.category = category;
            return this;
        }

        public Procedure build() {
            return Procedure.this;
        }
    }
}
