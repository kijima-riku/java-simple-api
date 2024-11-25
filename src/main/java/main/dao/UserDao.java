package main.java.main.dao;

import main.java.main.model.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
    private static final Map<String, UserModel> users = new HashMap<>();

    static {
        users.put("1", new UserModel("1", "John Doe", "john.doe@example.com"));
        users.put("2", new UserModel("2", "Jane Smith", "jane.smith@example.com"));
        users.put("3", new UserModel("3", "Bob Johnson", "bob.johnson@example.com"));
    }

    public List<UserModel> findAllUsers() {
        return new ArrayList<>(users.values());
    }

    public UserModel findUserById(String id) {
        return users.get(id);
    }
}