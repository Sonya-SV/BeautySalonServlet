package com.training.salon.model.dao.impl;

import com.training.salon.model.dao.ProcedureDao;
import com.training.salon.model.entity.Category;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.User;
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
    public List<Procedure> findAllByCategory(Category category) {
        Map<Long, Procedure> procedures = new HashMap<>();
        final String query = "select * from proced inner join category on category.id = category_id where category_name = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, category.getName());
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
