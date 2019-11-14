package com.training.salon.model.mapper;

import com.training.salon.model.entity.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleMapper implements ObjectMapper<Schedule> {
    @Override
    public Schedule extractFromResultSet(ResultSet rs) throws SQLException {
        return Schedule.builder()
                .id(rs.getLong("schedule_id"))
                .master(new MasterMapper().extractFromResultSet(rs))
                .user(new UserMapper().extractFromResultSet(rs))
                .procedure(new ProcedureMapper().extractFromResultSet(rs))
                .date(rs.getDate("date").toLocalDate())
                .time(rs.getTime("time").toLocalTime())
                .isDone(rs.getBoolean("done"))
                .build();
    }
}
