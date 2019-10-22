package com.training.salon.model.dao.impl;

import com.training.salon.model.dao.UserDao;
import com.training.salon.model.entity.User;
import com.training.salon.model.mapper.UserMapper;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private Connection connection;



    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) throws SQLException {
        final String query = "insert into user(email, first_name, last_name, password, role) values (?,?,?,?,?)";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString (1, entity.getEmail());
            st.setString   (2, entity.getFirstName());
            st.setString(3, entity.getLastName());
            st.setString(4, entity.getPassword());
            st.setString(5, entity.getRole().name());
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        final String query = "select * from user where id=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong (1, id);
            UserMapper userMapper = new UserMapper();
            ResultSet rs = st.executeQuery();
            Optional<User> user=Optional.empty();
            if(rs.next())
                user=Optional.of(userMapper.extractFromResultSet(rs));
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<User> findAll() {
        Map<Long, User> users = new HashMap<>();
        final String query = " select * from user";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                users.putIfAbsent(user.getId(), user);
            }
               return new ArrayList<>(users.values());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void update(User entity) throws SQLException {
        final String query = "update user set first_name=?, last_name=?, password=? where email=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString (1, entity.getFirstName());
            st.setString (2, entity.getLastName());
            st.setString (3, entity.getPassword());
            st.setString (4, entity.getEmail());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
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
