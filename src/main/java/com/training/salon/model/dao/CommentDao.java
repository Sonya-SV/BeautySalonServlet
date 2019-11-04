package com.training.salon.model.dao;

import com.training.salon.model.entity.Comment;

public interface CommentDao extends GenericDao<Comment> {
    void createComment(String comment, Long masterId, Long userId);
}
