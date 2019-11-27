package com.training.salon.model.dao.impl;

import com.training.salon.model.dao.CommentDao;
import com.training.salon.model.entity.Comment;
import com.training.salon.model.entity.User;
import com.training.salon.model.mapper.CommentMapper;
import com.training.salon.model.mapper.UserMapper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class JDBCCommentDao implements CommentDao {

    private Connection connection;

    public JDBCCommentDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Comment> findAllByMaster(Long masterId) {
        List<Comment> comments = new ArrayList<>();
        final String query = " select * from comment inner join user using (user_id) " +
                "inner join master using (master_id) " +
                "inner join category using (category_id)" +
                "where master_id=? order by date desc";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, masterId);
            ResultSet rs = st.executeQuery();
            CommentMapper commentMapper = new CommentMapper();

            while (rs.next())
                comments.add(commentMapper.extractFromResultSet(rs));
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Comment entity) {
        final String query = "insert into comment(user_id, master_id, comment, date) values (?,?,?,?)";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, entity.getUser().getId());
            st.setLong(2, entity.getMaster().getId());
            st.setString(3, entity.getComment());
            st.setTimestamp(4, Timestamp.valueOf(entity.getDateTime()));
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public void update(Comment entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
