package com.training.salon.model.service;

import com.training.salon.model.dao.DaoFactory;
import com.training.salon.model.dao.UserDao;
import com.training.salon.model.entity.User;

import java.sql.SQLException;
import java.util.List;
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
    public List<User> getAllUsers(){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    public void update(String firstName, String lastName, String password, User user) throws SQLException {
        UserDao dao = daoFactory.createUserDao();
        Optional<User> userToUpdate = dao.findById(user.getId());
        if(!userToUpdate.isEmpty()){
            User userr = userToUpdate.get();
            userr.setFirstName(firstName);
            userr.setLastName(lastName);
            userr.setPassword(password);
            dao.update(userr);
        }
    }
}
