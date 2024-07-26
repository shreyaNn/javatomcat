package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginFailureTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        
        // Connect to the Selenium Grid hub
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }

    @Test
    public void testLoginFailure() {
        driver.get("http://tomcat:8888/login-web-app-1.0-SNAPSHOT"); // Adjust URL as needed

        // Enter invalid username and password
        driver.findElement(By.name("username")).sendKeys("invalidUser");
        driver.findElement(By.name("password")).sendKeys("invalidPassword");
        driver.findElement(By.name("submit")).click();
        
        // Verify that login failed
        // Adjust the following line according to how your application shows login failures
        String errorMessage = driver.findElement(By.id("error-message")).getText();
        Assert.assertTrue(errorMessage.contains("Invalid credentials"), "Error message not displayed as expected.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

