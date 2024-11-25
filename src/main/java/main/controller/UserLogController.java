package main.java.main.controller;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import main.java.main.service.UserLogService;
import main.java.main.validation.UserLogValidation;
import main.java.main.model.UserLogModel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;


public class UserLogController implements HttpHandler {
    private final UserLogService userLogService = new UserLogService();
    private final UserLogValidation userLogValidation = new UserLogValidation();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        if ("/api/userLog".equals(path) && "GET".equals(method)) {
            handleGetAllUserLogs(exchange);
        } else if (path.startsWith("/api/userLog/") && "GET".equals(method)) {
            handleGetUserLogById(exchange);
        } else {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }

    private void handleGetAllUserLogs(HttpExchange exchange) throws IOException {
        List<UserLogModel> userLogs = userLogService.getAllUserLogs();
        String response = userLogs.stream().map(UserLogModel::toString).collect(Collectors.joining("\n"));
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void handleGetUserLogById(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        if (pathParts.length == 4) {
            String logId = pathParts[3];

            if (userLogValidation.isValidLogId(logId)) {
                UserLogModel userLog = userLogService.getUserLogById(logId);
                String response = userLog != null ? userLog.toString() : "User log not found";
                exchange.sendResponseHeaders(userLog != null ? 200 : 404, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(400, -1); // Bad Request
            }
        } else {
            exchange.sendResponseHeaders(404, -1); // Not Found
        }
    }
}