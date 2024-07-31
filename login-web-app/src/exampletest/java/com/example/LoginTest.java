package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import java.net.URL;
import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        try {
            System.out.println("Attempting to connect to Selenium hub at: http://localhost:4444/wd/hub");
            URL hubUrl = new URL("http://localhost:4444/wd/hub");
            System.out.println("Hub URL created successfully");
            driver = new RemoteWebDriver(hubUrl, options);
            System.out.println("RemoteWebDriver created successfully");
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            System.out.println("WebDriverWait created successfully");
        } catch (Exception e) {
            System.err.println("Failed to initialize RemoteWebDriver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testLoginSuccess() {
        try {
            String baseUrl = System.getProperty("app.url", "http://localhost:8888");
            driver.get(baseUrl + "/login");
            System.out.println("Navigated to: " + baseUrl + "/login");

            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username"))).sendKeys("user1");
            System.out.println("Entered username");

            driver.findElement(By.name("password")).sendKeys("changeme");
            System.out.println("Entered password");

            driver.findElement(By.name("submit")).click();
            System.out.println("Clicked submit button");

            // Adjust this to match your actual success page title
            String expectedTitle = "Login Success";
            wait.until(ExpectedConditions.titleIs(expectedTitle));
            String actualTitle = driver.getTitle();

            Assert.assertEquals(actualTitle, expectedTitle, "Login was not successful");
            System.out.println("Login successful, title matches expected");
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to exception", e);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("WebDriver closed successfully");
        }
    }
}

