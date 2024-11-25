package main.java.main.dao;

import main.java.main.model.UserLogModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserLogDao {
    private static final Map<String, UserLogModel> userLogs = new HashMap<>();

    static {
        userLogs.put("1", new UserLogModel("1", "Login", "User 1 logged in"));
        userLogs.put("2", new UserLogModel("2", "Logout", "User 2 logged out"));
        userLogs.put("3", new UserLogModel("3", "Password Change", "User 3 changed password"));
    }

    public List<UserLogModel> findAllUserLogs() {
        return new ArrayList<>(userLogs.values());
    }

    public UserLogModel findUserLogById(String id) {
        return userLogs.get(id);
    }
}