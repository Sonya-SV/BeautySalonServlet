package com.training.salon.model.mapper;

import com.training.salon.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements ObjectMapper<Category> {
    @Override
    public Category extractFromResultSet(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setName(rs.getString("category_name"));
        category.setImage(rs.getBlob("image"));
        return category;
    }
}
