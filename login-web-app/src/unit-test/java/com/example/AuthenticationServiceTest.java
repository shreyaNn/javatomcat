package com.example;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class AuthenticationServiceTest {
    private AuthenticationService authService;

    @Before
    public void setUp() {
        System.out.println("Setting up test");
        authService = new AuthenticationService();
    }

    @Test
    public void testAuthenticateValidUser() {
        String username = "testuser";
        String password = "testpass";
        System.out.println("Testing valid user: username=" + username + ", password=" + password);
        assertTrue("Authentication should succeed for valid user", 
                   authService.authenticate(username, password));
    }

    @Test
    public void testAuthenticateInvalidUser() {
        String username = "invaliduser";
        String password = "invalidpass";
        System.out.println("Testing invalid user: username=" + username + ", password=" + password);
        assertFalse("Authentication should fail for invalid user", 
                    authService.authenticate(username, password));
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down test");
    }
}
