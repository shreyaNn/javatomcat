package com.example;
import java.time.Duration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.time.Duration;
import java.util.logging.Logger;

public class LoginFailureTest extends BaseTest {
    private WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(LoginFailureTest.class.getName());
    
    @Override
    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) throws Exception {
        super.setUp(browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testFailedLogin() {
        logger.info("Starting failed login test");

        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        //WebElement loginButton = driver.findElement(By.cssSelector("input[type='submit']"));
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-login")));

        usernameField.sendKeys("user1");
        passwordField.sendKeys("invalidpassword");
        loginButton.click();

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement failureMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h2")));

        try {
            wait.until(ExpectedConditions.textToBe(By.tagName("h2"), "Login Failed!"));
        } catch (Exception e) {
            logger.warning("Expected text not found. Current text: " + failureMessage.getText());
        }

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("login_failure.png"));
            logger.info("Screenshot saved as login_failure.png");
        } catch (Exception e) {
            logger.warning("Failed to save screenshot: " + e.getMessage());
        }

        logger.info("Page source after login attempt: " + driver.getPageSource());

        Assert.assertEquals(failureMessage.getText(), "Login Failed", "Login failure message is incorrect");
        logger.info("Failed login test completed");
    }
}
