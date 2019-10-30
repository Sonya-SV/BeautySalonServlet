package com.training.salon.model.dao.impl;

import com.training.salon.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public ProcedureDao createProcedureDao() {
        return new JDBCProcedureDao(getConnection());
    }

    @Override
    public CategoryDao createCategoryDao() {
        return new JDBCCategoryDao(getConnection());
    }

    @Override
    public MasterDao createMasterDao() {
        return new JDBCMasterDao(getConnection());
    }

    @Override
    public ScheduleDao createScheduleDao() {
        return new JDBCScheduleDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
