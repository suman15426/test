package com.handlers;

import java.io.IOException;
import java.util.Map;

import com.services.UserService;
import com.shortner.Server;
import com.sun.net.httpserver.HttpHandler;
import com.uti.Utils;
import com.sun.net.httpserver.HttpExchange;

public class AuthHandler implements HttpHandler {
	

    private final UserService userService = new UserService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            Map<String, String> params = Utils.parseFormData(exchange);
            String username = params.get("username");
            String password = params.get("password");

            boolean success = userService.authenticate(username, password);
            if (success) {
                Utils.setSession(exchange, username);
                Utils.sendResponse(exchange, 200, "Login successful");
            } else {
                Utils.sendResponse(exchange, 401, "Invalid credentials");
            }
        }
    }
}