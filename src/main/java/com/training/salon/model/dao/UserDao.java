package com.training.salon.model.dao;

import com.training.salon.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByEmailAndPassword(String email, String password);
    List<User> findAllMasters();
}
