package com.shortner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.handlers.AuthHandler;
import com.handlers.StaticHandler;
import com.handlers.UrlHandler;
import java.util.logging.Logger;

public class Server {

	private static final Logger logger = Logger.getLogger(Server.class.getName());
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/shorten", new UrlHandler());
        server.createContext("/login", new AuthHandler());
        server.createContext("/static", new StaticHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        logger.info("Server started");
}
}