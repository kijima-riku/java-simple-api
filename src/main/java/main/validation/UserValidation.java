package main.java.main.validation;

public class UserValidation {
    public boolean isValidUserId(String id) {
        return id != null && id.matches("\\d+");
    }
}