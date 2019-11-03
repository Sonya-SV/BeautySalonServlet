package com.training.salon.model.service;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.dao.DaoFactory;
import com.training.salon.model.dao.ProcedureDao;
import com.training.salon.model.dao.ScheduleDao;
import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.Schedule;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class ScheduleService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveToSchedule(LocalTime time, LocalDate date, Long userId, Long masterId,
                               Long procedureId, boolean done, String comment) throws BookException {

        try (ScheduleDao dao = daoFactory.createScheduleDao()) {
            dao.saveToSchedule(time, date, userId, masterId, procedureId,done,comment);

        }
    }

    public List<Schedule> getScheduleForMaster(Long masterId) {

        try (ScheduleDao dao = daoFactory.createScheduleDao()) {
            return dao.getSchedule( masterId);

        }
    }
    public void makeNoteDone(Long scheduleId) {

        try (ScheduleDao dao = daoFactory.createScheduleDao()) {
            dao.makeDone(scheduleId);

        }
    }
    public Optional<Schedule> getScheduleNote(Long scheduleId) {

        try (ScheduleDao dao = daoFactory.createScheduleDao()) {
            return dao.findById(scheduleId);

        }
    }


}
