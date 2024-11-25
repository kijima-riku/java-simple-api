package main.java.main.model;

public class UserLogModel {
    private String id;
    private String type;
    private String description;

    public UserLogModel(String id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserLog{id='" + id + "', type='" + type + "', description='" + description + "'}";
    }
}