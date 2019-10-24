package com.training.salon.model.mapper;

import com.training.salon.model.entity.Category;
import com.training.salon.model.entity.Procedure;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcedureMapper implements ObjectMapper<Procedure> {
    @Override
    public Procedure extractFromResultSet(ResultSet rs) throws SQLException {
        Procedure procedure = new Procedure();
        procedure.setId(rs.getLong("id"));
        procedure.setName(rs.getString("name"));
        procedure.setPrice(rs.getBigDecimal("price"));

        Category category = new Category();
        category.setId(rs.getLong("category_id"));
        category.setName(rs.getString("category_name"));
        category.setImage(rs.getBlob("image"));
        procedure.setCategory(category);

        return procedure;
    }
}
