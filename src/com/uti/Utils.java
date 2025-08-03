package com.uti;



import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.util.*;

public class Utils {

    public static Map<String, String> parseFormData(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        Scanner scanner = new Scanner(is).useDelimiter("\\A");
        String body = scanner.hasNext() ? scanner.next() : "";
        Map<String, String> params = new HashMap<>();
        for (String pair : body.split("&")) {
            String[] kv = pair.split("=");
            if (kv.length == 2) {
                params.put(kv[0], URLDecoder.decode(kv[1], "UTF-8"));
            }
        }
        return params;
    }

    public static void sendResponse(HttpExchange exchange, int statusCode, String message) throws IOException {
        exchange.sendResponseHeaders(statusCode, message.length());
        exchange.getResponseBody().write(message.getBytes());
        exchange.getResponseBody().close();
    }

    public static void setSession(HttpExchange exchange, String username) {
        exchange.getResponseHeaders().add("Set-Cookie", "user=" + username + "; Path=/");
    }

    public static String getSessionUser(HttpExchange exchange) {
        List<String> cookies = exchange.getRequestHeaders().get("Cookie");
        if (cookies != null) {
            for (String cookie : cookies) {
                for (HttpCookie c : HttpCookie.parse(cookie)) {
                    if ("user".equals(c.getName())) {
                        return c.getValue();
                    }
                }
            }
        }
        return null;
    }
}
