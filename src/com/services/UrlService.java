package com.services;

import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import com.db.Database;

public class UrlService {
	private static final Logger logger = LoggerFactory.getLogger(UrlService.class);

    private final Connection conn = Database.getConnection();

    public String shortenUrl(String longUrl, String customAlias, String user) {
        String alias = customAlias != null ? customAlias : UUID.randomUUID().toString().substring(0, 6);
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO urls (alias, long_url, user) VALUES (?, ?, ?)")) {
            stmt.setString(1, alias);
            stmt.setString(2, longUrl);
            stmt.setString(3, user);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LoggerFactory.getLogger(UrlService.class).error("Error shortening URL", e);
        }
        return "http://localhost:8080/" + alias;
    }
}