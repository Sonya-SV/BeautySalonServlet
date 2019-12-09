package com.training.salon.model.dao.impl;

import com.training.salon.model.dao.UserDao;
import com.training.salon.model.entity.User;
import com.training.salon.model.mapper.UserMapper;
import org.mindrot.jbcrypt.BCrypt;

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
            st.setString(1, entity.getEmail());
            st.setString(2, entity.getFirstName());
            st.setString(3, entity.getLastName());
            st.setString(4, entity.getPassword());
            st.setString(5, entity.getRole().name());
            st.execute();

        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        final String query = "select * from user where user_id=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);
            UserMapper userMapper = new UserMapper();
            ResultSet rs = st.executeQuery();
            Optional<User> user = Optional.empty();
            if (rs.next())
                user = Optional.of(userMapper.extractFromResultSet(rs));
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        final String query = " select * from user";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            UserMapper userMapper = new UserMapper();

            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void update(User entity) throws SQLException {
        final String query = "update user set first_name=?, last_name=?, password=? where email=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, entity.getFirstName());
            st.setString(2, entity.getLastName());
            st.setString(3, entity.getPassword());
            st.setString(4, entity.getEmail());
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

    @Override
    public Optional<User> findByEmailAndPassword(String email) {
        final String query = "select * from user where email=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, email);
            UserMapper userMapper = new UserMapper();

            ResultSet rs = st.executeQuery();
            Optional<User> user = Optional.empty();
            if (rs.next())
                user = Optional.of(userMapper.extractFromResultSet(rs));
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAllMasters() {
       List<User> users = new ArrayList<>();
        final String query = " select * from user where role= ? ";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, User.Role.MASTER.name());
            ResultSet rs = st.executeQuery();
            UserMapper userMapper = new UserMapper();

            while (rs.next())
                users.add(userMapper.extractFromResultSet(rs));
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
