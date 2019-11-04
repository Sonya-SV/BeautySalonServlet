package com.training.salon.model.dao.impl;

import com.training.salon.model.dao.MasterDao;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.User;
import com.training.salon.model.mapper.MasterMapper;
import com.training.salon.model.mapper.UserMapper;

import java.io.PipedReader;
import java.sql.*;
import java.util.*;

public class JDBCMasterDao implements MasterDao {
    private Connection connection;

    public JDBCMasterDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Master entity) throws SQLException {

    }

    @Override
    public Optional<Master> findById(Long id) {
        final String query = " select * from master inner join user using (user_id) where master_id = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            MasterMapper masterMapper = new MasterMapper();
            Optional<Master> master = Optional.empty();
            if (rs.next()) {
                 master = Optional.of(masterMapper.extractFromResultSet(rs));
            }
            return master;
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }

    @Override
    public List<Master> findAll() {
        Map<Long, Master> masters = new HashMap<>();
        final String query = " select * from master inner join user using (user_id)";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            MasterMapper masterMapper = new MasterMapper();

            while (rs.next()) {
                Master master = masterMapper.extractFromResultSet(rs);
                masters.putIfAbsent(master.getId(), master);
            }
            return new ArrayList<>(masters.values());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Master entity) throws SQLException {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public Optional<Master> getMaster(Long userId) {
        final String query = " select * from master inner join user using (user_id) where user_id = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, userId);
            ResultSet rs = st.executeQuery();

            MasterMapper masterMapper = new MasterMapper();
            Optional<Master> master = Optional.empty();
            if (rs.next()) {
                master = Optional.of(masterMapper.extractFromResultSet(rs));
            }
            return master;
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }
}

