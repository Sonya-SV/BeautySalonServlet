package com.training.salon.model.mapper;

import com.training.salon.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {


    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPassword(rs.getString("password"));

        for (User.Role role : User.Role.values())
            if (role.name().equals(rs.getObject("role")))
                user.setRole(role);
        return user;
    }

}
