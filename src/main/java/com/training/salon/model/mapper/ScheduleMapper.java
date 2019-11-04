package com.training.salon.model.mapper;

import com.training.salon.model.entity.Procedure;
import com.training.salon.model.entity.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleMapper implements ObjectMapper<Schedule> {
    @Override
    public Schedule extractFromResultSet(ResultSet rs) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getLong("schedule_id"));
        schedule.setMaster(new MasterMapper().extractFromResultSet(rs));
        schedule.setUser(new UserMapper().extractFromResultSet(rs));
        schedule.setProcedure(new ProcedureMapper().extractFromResultSet(rs));
        schedule.setDate(rs.getDate("date").toLocalDate());
        schedule.setTime(rs.getTime("time").toLocalTime());
        schedule.setDone(rs.getBoolean("done"));

        return schedule;

    }
}
