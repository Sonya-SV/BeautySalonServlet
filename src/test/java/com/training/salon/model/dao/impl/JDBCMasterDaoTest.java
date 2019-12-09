package com.training.salon.model.dao.impl;

import com.training.salon.controller.exception.DiscrepancyException;
import com.training.salon.model.entity.Master;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class JDBCMasterDaoTest {
    private Connection connection;
    private JDBCMasterDao dao;

    @Before
    public void before() {
        try {
            String user = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost:3306/beauty_salon?useTimezone=true&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, user, password);
            dao = new JDBCMasterDao(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = DiscrepancyException.class)
    public void isProcedureAccordToMasterTest1() throws DiscrepancyException {
        final Long masterId = 2L;
        final Long procedureId = 3L;

        dao.isProcedureAccordToMaster(masterId, procedureId);
    }

    @Test
    public void isProcedureAccordToMasterTest2() {
        final Long masterId = 1L;
        final Long procedureId = 1L;
        try {
            dao.isProcedureAccordToMaster(masterId, procedureId);
        } catch (DiscrepancyException e) {
            fail();
        }

    }

    @Test(expected = DiscrepancyException.class)
    public void checkTimeForMasterTest() throws DiscrepancyException {
        final Long masterId = 1L;
        final LocalTime time = LocalTime.of(22, 0);
        dao.checkTimeForMaster(masterId, time);


    }

    @Test
    public void checkTimeForMasterTest2() {
        final Long masterId = 1L;
        final LocalTime time = LocalTime.of(17, 0);
        try {
            dao.checkTimeForMaster(masterId, time);
        } catch (DiscrepancyException e) {
            fail();
        }

    }
    @Test
    public void getMastersByCategory() {
        final Long categoryId = 1L;

        List<Long> mastersId= new ArrayList<>();
        for(Master m: dao.getMastersByCategory(categoryId))
            mastersId.add( m.getId());
        List<Long> expected = new ArrayList<>(Arrays.asList(1L, 5L, 9L));
        assertThat(mastersId, is(expected));

    }


}
