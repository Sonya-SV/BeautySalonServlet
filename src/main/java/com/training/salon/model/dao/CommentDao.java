package com.training.salon.model.dao;

import com.training.salon.model.entity.Comment;

import java.util.List;

public interface CommentDao extends GenericDao<Comment> {
    List<Comment> findAllByMaster(Long masterId);
}
