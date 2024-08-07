package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    public void init() throws ServletException {
        try (Connection conn = DatabaseUtil.getConnection()) {
            logger.info("Database connection successful");
        } catch (SQLException e) {
            logger.error("Database connection failed: " + e.getMessage(), e);
            throw new ServletException("Database connection failed", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Forwarding to login.jsp");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean loginSuccess = authenticate(username, password);
        
        if (loginSuccess) {
            logger.info("Successful login attempt for user: {}", username);
        } else {
            logger.warn("Failed login attempt for user: {}", username);
        }

        request.setAttribute("loginSuccess", loginSuccess);
        request.setAttribute("username", username);
        request.getRequestDispatcher("/loginResult.jsp").forward(request, response);
    }

    private boolean authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logger.error("Authentication error: " + e.getMessage(), e);
            return false;
        }
    }
}
