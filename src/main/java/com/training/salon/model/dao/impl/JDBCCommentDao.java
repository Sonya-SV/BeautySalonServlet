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
    public void createComment(String comment, Long masterId, Long userId) {
        final String query = "insert into comment(user_id, master_id, comment, date) values (?,?,?,?)";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, userId);
            st.setLong(2, masterId);
            st.setString(3, comment);
            st.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> findAllByMaster(Long masterId) {
        Map<Long, Comment> comments = new HashMap<>();
        final String query = " select * from comment inner join user using (user_id) " +
                "inner join master using (master_id) " +
                "where master_id=? order by date desc";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, masterId);
            ResultSet rs = st.executeQuery();

            CommentMapper commentMapper = new CommentMapper();

            while (rs.next()) {
                Comment comment = commentMapper.extractFromResultSet(rs);
                comments.putIfAbsent(comment.getId(), comment);
            }
            return new ArrayList<>(comments.values());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void create(Comment entity) throws SQLException {

    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Comment> findAll() {
//        Map<Long, Comment> comments = new HashMap<>();
//        final String query = " select * from comment inner join user using (user_id) " +
//                "inner join (SELECT * FROM USER inner join master using (user_id))as s on comment.master_id=s.master_id";
//        try (Statement st = connection.createStatement()) {
//            ResultSet rs = st.executeQuery(query);
//
//            CommentMapper commentMapper = new CommentMapper();
//
//            while (rs.next()) {
//               Comment comment = commentMapper.extractFromResultSet(rs);
//                comments.putIfAbsent(comment.getId(), comment);
//            }
//            return new ArrayList<>(comments.values());
//
//        } catch (SQLException e) {
//            e.printStackTrace();
            return null;
//        }
    }

    @Override
    public void update(Comment entity) throws SQLException {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
//            logger.warn("close() SQLException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
