package com.training.salon.model.mapper;

import com.training.salon.model.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;

public class CommentMapper implements ObjectMapper<Comment> {
    @Override
    public Comment extractFromResultSet(ResultSet rs) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getLong("comment_id"));
        comment.setMaster(new MasterMapper().extractFromResultSet(rs));
        comment.setUser(new UserMapper().extractFromResultSet(rs));
        comment.setComment(rs.getString("comment"));
        comment.setDateTime(LocalDateTime.of(rs.getDate("date").toLocalDate(),
                rs.getTime("date").toLocalTime()));

        return comment;
    }
}
