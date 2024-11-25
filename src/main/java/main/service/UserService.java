package main.java.main.service;

import main.java.main.dao.UserDao;
import main.java.main.model.UserModel;

import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDao();

    public List<UserModel> getAllUsers() {
        return userDao.findAllUsers();
    }

    public UserModel getUserById(String id) {
        return userDao.findUserById(id);
    }
}