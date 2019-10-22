package com.training.salon.model.service;

import com.training.salon.model.dao.DaoFactory;
import com.training.salon.model.dao.UserDao;
import com.training.salon.model.entity.User;

import java.sql.SQLException;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveUser(User user) throws SQLException {
        UserDao dao = daoFactory.createUserDao();
        dao.create(user);
    }
}
