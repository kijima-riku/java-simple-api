package main.java.main.validation;

public class UserLogValidation {
    public boolean isValidLogId (String id) {
        return id != null && id.matches("\\d+");
    }
}
