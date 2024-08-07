package com.example;

public class AuthenticationService {
    public boolean authenticate(String username, String password) {
        // Simple authentication logic for testing
        return "testuser".equals(username) && "testpass".equals(password);
    }
}
