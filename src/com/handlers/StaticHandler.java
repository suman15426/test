package com.handlers;
import com.handlers.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

public class StaticHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "<html><body><h1>Welcome to the Static Page!</h1></body></html>";
        exchange.getResponseHeaders().add("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
    }
}