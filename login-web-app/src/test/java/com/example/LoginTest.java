package com.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        
        // Connect to the Selenium Grid hub
        driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), capabilities);
    }

    @Test
    public void testLoginSuccess() {
        //driver.get("http://localhost:8888/login-web-app"); // Adjust URL as needed
        driver.get("http://tomcat:8888/login-web-app-1.0-SNAPSHOT");
        driver.findElement(By.name("username")).sendKeys("user1");
        driver.findElement(By.name("password")).sendKeys("changeme");
        driver.findElement(By.name("submit")).click();
        String title = driver.getTitle();
        Assert.assertEquals(title, "Expected Title After Login");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
