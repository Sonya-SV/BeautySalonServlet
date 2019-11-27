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
    public void create(Procedure entity) {

    }

    @Override
    public Optional<Procedure> findById(Long id) {
        final String query = "select * from proced inner join category using (category_id) where proced_id=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);
            ProcedureMapper procedureMapper = new ProcedureMapper();
            ResultSet rs = st.executeQuery();
            Optional<Procedure> procedure = Optional.empty();

            if (rs.next())
                procedure = Optional.of(procedureMapper.extractFromResultSet(rs));
            return procedure;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<Procedure> findAll() {
        return null;
    }

    @Override
    public void update(Procedure entity) {

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
    public List<Procedure> findAllByCategory(Long categoryId) {
        List<Procedure> procedures = new ArrayList<>();
        final String query = "select * from proced inner join category using (category_id) where category_id = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, categoryId);
            ResultSet rs = st.executeQuery();

            ProcedureMapper procedureMapper = new ProcedureMapper();

            while (rs.next())
                procedures.add(procedureMapper.extractFromResultSet(rs));
            return procedures;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Procedure> findAllProceduresByMaster(Long masterId) {
        List<Procedure> procedures = new ArrayList<>();
        final String query = " select * from proced inner join master  " +
                "using (category_id) " +
                "inner join category " +
                "using (category_id) " +
                "where master_id=?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, masterId);
            ResultSet rs = st.executeQuery();
            ProcedureMapper procedureMapper = new ProcedureMapper();

            while (rs.next())
                procedures.add(procedureMapper.extractFromResultSet(rs));
            return procedures;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
