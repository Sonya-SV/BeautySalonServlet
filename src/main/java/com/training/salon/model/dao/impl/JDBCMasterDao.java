package com.training.salon.model.dao.impl;

import com.training.salon.controller.exception.DiscrepancyException;
import com.training.salon.model.dao.MasterDao;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.User;
import com.training.salon.model.mapper.MasterMapper;
import com.training.salon.model.mapper.UserMapper;

import java.io.PipedReader;
import java.sql.*;
import java.time.LocalTime;
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
            rs.close();
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
        try {
            connection.close();
        } catch (SQLException e) {
//            logger.warn("close() SQLException: " + e.getMessage());
            throw new RuntimeException(e);
        }
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

    @Override
    public List<Master> getMastersByCategory(Long categoryId) {
        Map<Long, Master> masters = new HashMap<>();
        final String query = " select * from master inner join user using (user_id)" +
                "inner join category using (category_id) where category_id=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, categoryId);
            ResultSet rs = st.executeQuery();

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
    public void isProcedureAccordToMaster(Long masterId, Long procedureId) throws DiscrepancyException {
        final String queryCheckDiscrepancy = "SELECT * FROM master inner join proced using (category_id) where proced_id =? and master_id = ?";

        try(PreparedStatement st = connection.prepareStatement(queryCheckDiscrepancy)) {
            st.setLong(1, procedureId);
            st.setLong(2, masterId);
            ResultSet rs = st.executeQuery();
            if(!rs.next())
                throw new DiscrepancyException();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkTimeForMaster(Long masterId, LocalTime time) throws DiscrepancyException {
        final String query = "SELECT * FROM master where master_id = ? and time_start>=? and time_end<?";
        try(PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, masterId);
            st.setTime(2, Time.valueOf(time));
            st.setTime(3, Time.valueOf(time));
            ResultSet rs = st.executeQuery();
            if(!rs.next())
                throw new DiscrepancyException();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

