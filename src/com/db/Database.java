package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:h2:./urlshortener");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS urls (alias VARCHAR PRIMARY KEY, long_url VARCHAR, user VARCHAR)");
            stmt.execute("CREATE TABLE IF NOT EXISTS users (username VARCHAR PRIMARY KEY, password VARCHAR)");
        } catch (SQLException e) {
            LoggerFactory.getLogger(Database.class).error("DB init error", e);
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}