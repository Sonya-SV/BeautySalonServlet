package com.training.salon.model.mapper;

import com.training.salon.model.entity.Master;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class MasterMapper implements ObjectMapper<Master>{

    @Override
    public Master extractFromResultSet(ResultSet rs) throws SQLException {
        Master master = new Master();
        master.setId(rs.getLong("id"));
        master.setUser(new UserMapper().extractFromResultSet(rs));
        master.setTimeStart(rs.getTime("time_start").toLocalTime());
        master.setTimeEnd(rs.getTime("time_end").toLocalTime());
        master.setPhoto(new String(Base64.getEncoder().encode(rs.getBytes("photo")), StandardCharsets.UTF_8));

        return master;
    }
}
