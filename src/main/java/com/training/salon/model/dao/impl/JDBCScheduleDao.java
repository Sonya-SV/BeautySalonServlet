package com.training.salon.model.dao.impl;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.dao.ScheduleDao;
import com.training.salon.model.entity.Schedule;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

public class JDBCScheduleDao implements ScheduleDao {

    private Connection connection;

    public JDBCScheduleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Schedule entity) throws SQLException{
//        final String queryInsert = "insert into schedule(date, time, master_id, user_id, proced_id, done, comment) values (?,?,?,?,?,?,?)";
//        final String queryCheck = "select * from schedule where date=? and time = ?";
//
//        try (PreparedStatement stInsert = connection.prepareStatement(queryInsert);
//             PreparedStatement stCheck = connection.prepareStatement(queryCheck)) {
//            stInsert.setDate(1, Date.valueOf(entity.getDate()));
//            stInsert.setTime(2, Time.valueOf(entity.getTime()));
//            stInsert.setLong(3, entity.getMaster().getId());
//            stInsert.setLong(4, entity.getUser().getId());
//            stInsert.setLong(5, entity.getProcedure().getId());
//            stInsert.setBoolean(6, entity.isDone());
//            stInsert.setString(7, entity.getComment());
//
//            stCheck.setDate(1, Date.valueOf(entity.getDate()));
//            stCheck.setTime(2, Time.valueOf(entity.getTime()));
//
//
//            connection.setAutoCommit(false);
//
//            ResultSet rs = stCheck.executeQuery();
//            if (rs.next())
//                throw new BookException();
//
//            stInsert.execute();
//
//            connection.commit();
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Schedule> findAll() {
        return null;
    }

    @Override
    public void update(Schedule entity) throws SQLException {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public void saveToSchedule(LocalTime time, LocalDate date, Long userId, Long masterId, Long procedureId, boolean done, String comment) throws BookException {
        final String queryInsert = "insert into schedule(date, time, master_id, user_id, proced_id, done, comment) values (?,?,?,?,?,?,?)";
        final String queryCheck = "select * from schedule where date=? and time = ?";

        try (PreparedStatement stInsert = connection.prepareStatement(queryInsert);
             PreparedStatement stCheck = connection.prepareStatement(queryCheck)) {
            stInsert.setDate(1, Date.valueOf(date));
            stInsert.setTime(2, Time.valueOf(time));
            stInsert.setLong(3, masterId);
            stInsert.setLong(4, userId);
            stInsert.setLong(5, procedureId);
            stInsert.setBoolean(6, done);
            stInsert.setString(7, comment);

            stCheck.setDate(1, Date.valueOf(date));
            stCheck.setTime(2, Time.valueOf(time));


            connection.setAutoCommit(false);

            ResultSet rs = stCheck.executeQuery();
            if (rs.next())
                throw new BookException();

            stInsert.execute();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
