package com.training.salon.model.dao;

import com.training.salon.model.entity.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Comment> {
    void createComment(String comment, Long masterId, Long userId);
    List<Comment> findAllByMaster(Long masterId);
}
