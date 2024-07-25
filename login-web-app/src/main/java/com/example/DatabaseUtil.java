package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/tododb";
    private static final String DB_USER = "user1";
    private static final String DB_PASSWORD = "changeme";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    }
}

