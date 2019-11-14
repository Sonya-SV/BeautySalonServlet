package com.training.salon.model.mapper;

import com.training.salon.model.entity.Procedure;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcedureMapper implements ObjectMapper<Procedure> {
    @Override
    public Procedure extractFromResultSet(ResultSet rs) throws SQLException {
        return Procedure.builder()
                .id(rs.getLong("proced_id"))
                .name(rs.getString("name"))
                .price(rs.getBigDecimal("price"))
                .category(new CategoryMapper().extractFromResultSet(rs))
                .build();
    }
}
