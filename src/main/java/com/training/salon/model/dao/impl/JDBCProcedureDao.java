package com.training.salon.model.dao.impl;

import com.training.salon.model.dao.ProcedureDao;
import com.training.salon.model.entity.Category;
import com.training.salon.model.entity.Master;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.User;
import com.training.salon.model.mapper.MasterMapper;
import com.training.salon.model.mapper.ProcedureMapper;
import com.training.salon.model.mapper.UserMapper;

import java.sql.*;
import java.util.*;

public class JDBCProcedureDao implements ProcedureDao {

    private Connection connection;

    public JDBCProcedureDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Procedure entity) throws SQLException {

    }

    @Override
    public Optional<Procedure> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Procedure> findAll() {
        return null;
    }

    @Override
    public void update(Procedure entity) throws SQLException {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public List<Procedure> findAllByCategory(Long categoryId) {
        Map<Long, Procedure> procedures = new HashMap<>();
        final String query = "select * from proced inner join category using (category_id) where category_id = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, categoryId);
            ResultSet rs = st.executeQuery();

            ProcedureMapper procedureMapper = new ProcedureMapper();

            while (rs.next()) {
                Procedure procedure = procedureMapper.extractFromResultSet(rs);
                procedures.putIfAbsent(procedure.getId(), procedure);
            }
            return new ArrayList<>(procedures.values());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Procedure> findAllProceduresByMaster(Long masterId) {
        Map<Long, Procedure> procedures = new HashMap<>();
        final String query = " select * from proced inner join master  " +
                "using (category_id) " +
                "inner join category " +
                "using (category_id) "+
                "where master_id=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, masterId);
            ResultSet rs = st.executeQuery();

            ProcedureMapper procedureMapper = new ProcedureMapper();

            while (rs.next()) {
               Procedure procedure = procedureMapper.extractFromResultSet(rs);
                procedures.putIfAbsent(procedure.getId(), procedure);
            }

            return new ArrayList<>(procedures.values());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
