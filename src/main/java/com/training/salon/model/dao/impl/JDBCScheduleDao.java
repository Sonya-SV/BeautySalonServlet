package com.training.salon.model.dao.impl;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.dao.ScheduleDao;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.Schedule;
import com.training.salon.model.mapper.ProcedureMapper;
import com.training.salon.model.mapper.ScheduleMapper;
import com.training.salon.model.mapper.UserMapper;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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
        final String query ="select * from schedule " +
        "inner join master using master_id " +
                "inner join user using (user_id) " +
                "inner join proced using (proced_id) " +
                "inner join category using (category_id) where schedule_id=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong (1, id);
            ScheduleMapper scheduleMapper = new ScheduleMapper();
            ResultSet rs = st.executeQuery();
            Optional<Schedule> note=Optional.empty();
            if(rs.next())
                note=Optional.of(scheduleMapper.extractFromResultSet(rs));
            return note;

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        final String queryInsert = "insert into schedule(date, time, master_id, user_id, proced_id, done) values (?,?,?,?,?,?)";
        final String queryCheck = "select * from schedule where date=? and time = ?";

        try (PreparedStatement stInsert = connection.prepareStatement(queryInsert);
             PreparedStatement stCheck = connection.prepareStatement(queryCheck)) {
            stInsert.setDate(1, Date.valueOf(date));
            stInsert.setTime(2, Time.valueOf(time));
            stInsert.setLong(3, masterId);
            stInsert.setLong(4, userId);
            stInsert.setLong(5, procedureId);
            stInsert.setBoolean(6, done);

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

    @Override
    public void makeDone(Long scheduleId) {
        final String query = "update schedule set done = true where schedule_id = ?";

        try (PreparedStatement st = connection.prepareStatement(query);
        ) {
            st.setLong(1, scheduleId);
            st.execute();
        } catch (SQLException e) {
            System.out.println("already done");
        }
    }

    @Override
    public List<Schedule> getSchedule(Long masterId) {

        Map<Long, Schedule> schedule = new HashMap<>();
        final String query = "select * from schedule " +
                "inner join master using (master_id) " +
                "inner join user using (user_id) " +
                "inner join proced on using (proced_id) " +
                "inner join category using (category_id) where master_id=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, masterId);
            ResultSet rs = st.executeQuery();

            ScheduleMapper scheduleMapper = new ScheduleMapper();

            while (rs.next()) {
                Schedule note = scheduleMapper.extractFromResultSet(rs);
                schedule.putIfAbsent(note.getId(), note);
            }
            return new ArrayList<>(schedule.values());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
