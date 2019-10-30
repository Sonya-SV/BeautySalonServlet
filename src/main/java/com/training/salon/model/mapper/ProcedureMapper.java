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
        procedure.setCategory(new CategoryMapper().extractFromResultSet(rs));

        return procedure;
    }
}
