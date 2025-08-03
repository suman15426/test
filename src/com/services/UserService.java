package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.Database;

public class UserService {
    private final Connection conn = Database.getConnection();

    public boolean authenticate(String username, String password) {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            LoggerFactory.getLogger(UserService.class).error("Auth error", e);
            return false;
        }
    }
}