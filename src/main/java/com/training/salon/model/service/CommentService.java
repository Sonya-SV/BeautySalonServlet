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

    public void createComment(String comment, Long masterId, Long userId){
        try (CommentDao dao = daoFactory.createCommentDao()) {
             dao.createComment(comment, masterId, userId);
        }
    }

    public List<Comment> getAllComments(){
        try (CommentDao dao = daoFactory.createCommentDao()) {
            return dao.findAll();
        }
    }

}
