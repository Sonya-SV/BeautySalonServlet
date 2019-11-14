package com.training.salon.model.mapper;

import com.training.salon.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {


    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("user_id"))
                .email(rs.getString("email"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .password(rs.getString("password"))
                .role(User.Role.valueOf(rs.getString("role")))
                .build();
    }

}
