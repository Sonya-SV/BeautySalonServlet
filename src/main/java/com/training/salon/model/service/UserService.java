package com.training.salon.model.service;

import com.training.salon.model.dao.DaoFactory;
import com.training.salon.model.dao.UserDao;
import com.training.salon.model.entity.User;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveUser(User user) throws SQLException {
        UserDao dao = daoFactory.createUserDao();
        dao.create(user);
    }

    public Optional<User> login(String email, String password){
        Optional<User> user;
        try(UserDao dao = daoFactory.createUserDao()){
            user = dao.findByEmailAndPassword(email, password);
        }
        return user;
    }
}
