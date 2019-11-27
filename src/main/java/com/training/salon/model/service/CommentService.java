package com.training.salon.model.service;

import com.training.salon.model.dao.CommentDao;
import com.training.salon.model.dao.DaoFactory;
import com.training.salon.model.dao.MasterDao;
import com.training.salon.model.entity.Comment;
import com.training.salon.model.entity.Master;

import java.sql.SQLException;
import java.util.List;

public class CommentService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public void createComment(Comment comment){
        try (CommentDao dao = daoFactory.createCommentDao()) {
             dao.create(comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Comment> getAllComments(){
        try (CommentDao dao = daoFactory.createCommentDao()) {
            return dao.findAll();
        }
    }
    public List<Comment> getAllByMaster(Long masterId){
        try (CommentDao dao = daoFactory.createCommentDao()) {
            return dao.findAllByMaster(masterId);
        }
    }

}
