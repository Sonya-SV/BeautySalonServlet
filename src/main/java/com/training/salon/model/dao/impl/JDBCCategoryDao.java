package com.training.salon.model.dao.impl;

import com.training.salon.model.dao.CategoryDao;
import com.training.salon.model.entity.Category;
import com.training.salon.model.mapper.CategoryMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class JDBCCategoryDao implements CategoryDao {

    private Connection connection;

    public JDBCCategoryDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Category entity) {

    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        final String query = " select * from category";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            CategoryMapper categoryMapper = new CategoryMapper();

            while (rs.next()) {
                Category category = categoryMapper.extractFromResultSet(rs);
                categories.add(category);
            }
            return categories;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Category entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
