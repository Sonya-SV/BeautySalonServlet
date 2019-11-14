package com.training.salon.model.mapper;

import com.training.salon.model.entity.Category;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class CategoryMapper implements ObjectMapper<Category> {
    @Override
    public Category extractFromResultSet(ResultSet rs) throws SQLException {
        return Category.builder()
                .id(rs.getLong("category_id"))
                .name(rs.getString("category_name"))
                .image(new String(Base64.getEncoder().encode(rs.getBytes("image")), StandardCharsets.UTF_8))
                .build();
    }
}
