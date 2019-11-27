package com.training.salon.model.mapper;

import com.training.salon.model.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CommentMapper implements ObjectMapper<Comment> {
    @Override
    public Comment extractFromResultSet(ResultSet rs) throws SQLException {
        return Comment.builder()
                .id(rs.getLong("comment_id"))
                .master(new MasterMapper().extractFromResultSet(rs))
                .user(new UserMapper().extractFromResultSet(rs))
                .comment(rs.getString("comment"))
                .dateTime(rs.getTimestamp("date").toLocalDateTime())
                .build();
    }
}
