package main.java.main.controller;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import main.java.main.service.UserService;
import main.java.main.validation.UserValidation;
import main.java.main.model.UserModel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;


public class UserController implements HttpHandler {

    private final UserService userService = new UserService();
    private final UserValidation userValidation = new UserValidation();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        if ("/api/user".equals(path) && "GET".equals(method)) {
            handleGetAllUsers(exchange);
        } else if (path.startsWith("/api/user/") && "GET".equals(method)) {
            handleGetUserById(exchange);
        } else {
            exchange.sendResponseHeaders(405, -1);
        }
    }

    private void handleGetAllUsers(HttpExchange exchange) throws IOException {
        List<UserModel> users = userService.getAllUsers();
        String response = users.stream().map(UserModel::toString).collect(Collectors.joining("\n"));
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void handleGetUserById(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");

        if (pathParts.length == 4) {
            String userId = pathParts[3];

            if (userValidation.isValidUserId(userId)) {
                UserModel user = userService.getUserById(userId);
                String response = user != null ? user.toString() : "User  not found";
                exchange.sendResponseHeaders(user != null ? 200 : 404, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(400, -1);
            }
        } else {
            exchange.sendResponseHeaders(404, -1);
        }
    }

}