package com.training.salon.model.dao;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleDao extends GenericDao<Schedule> {
    void saveToSchedule(LocalTime time, LocalDate date, Long userId, Long masterId, Long procedureId, boolean done, String comment) throws BookException;
    List<Schedule> getSchedule(Long masterId);
    void makeDone(Long scheduleId);
}
