package com.training.salon.model.dao;

import com.training.salon.controller.exception.BookException;
import com.training.salon.model.entity.Schedule;

import java.util.List;

public interface ScheduleDao extends GenericDao<Schedule> {
    void saveToSchedule(Schedule schedule) throws BookException;
    List<Schedule> getSchedule(Long masterId);
    void makeDone(Long scheduleId);

}
