package main.java.main;

import com.sun.net.httpserver.HttpServer;
import main.java.main.controller.UserController;
import main.java.main.controller.UserLogController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Routes {

    public static void main(String[] args) throws IOException {
        //HTTPサーバー
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

        UserController userController = new UserController();
        UserLogController userLogController = new UserLogController();

        //エンドポイントをコンテキストに設定
        server.createContext("/api/user", userController);
        server.createContext("/api/user/", userController);
        server.createContext("/api/userLog", userLogController);
        server.createContext("/api/userLog/", userLogController);

        //サーバー開始
        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port 8080");
    }
}