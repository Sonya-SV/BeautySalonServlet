package com.training.salon.model.mapper;

import com.training.salon.model.entity.Master;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class MasterMapper implements ObjectMapper<Master> {

    @Override
    public Master extractFromResultSet(ResultSet rs) throws SQLException {
        return Master.builder()
                .id(rs.getLong("master_id"))
                .user(new UserMapper().extractFromResultSet(rs))
                .timeStart(rs.getTime("time_start").toLocalTime())
                .timeEnd(rs.getTime("time_end").toLocalTime())
                .photo(new String(Base64.getEncoder().encode(rs.getBytes("photo")), StandardCharsets.UTF_8))
                .build();
    }
}
