package com.handlers;

import java.io.IOException;
import java.util.Map;

import com.services.UrlService;
import com.sun.net.httpserver.HttpHandler;
import com.uti.Utils;
import com.sun.net.httpserver.HttpExchange;

public class UrlHandler implements HttpHandler {
    private final UrlService urlService = new UrlService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            Map<String, String> params = Utils.parseFormData(exchange);
            String longUrl = params.get("url");
            String customAlias = params.get("custom");
            String user = Utils.getSessionUser(exchange);

            String shortUrl = urlService.shortenUrl(longUrl, customAlias, user);
            Utils.sendResponse(exchange, 200, shortUrl);
        }
    }
}
