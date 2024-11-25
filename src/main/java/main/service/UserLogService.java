package main.java.main.service;

import main.java.main.dao.UserLogDao;
import main.java.main.model.UserLogModel;

import java.util.List;

public class UserLogService {
    private final UserLogDao userLogDao = new UserLogDao();

    public List<UserLogModel> getAllUserLogs() {
        return userLogDao.findAllUserLogs();
    }

    public UserLogModel getUserLogById(String id) {
        return userLogDao.findUserLogById(id);
    }
}