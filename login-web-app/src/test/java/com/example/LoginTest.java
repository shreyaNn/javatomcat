package com.example;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.logging.Logger;

public class LoginTest extends BaseTest {
    private WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(LoginTest.class.getName());

    @Override
    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) throws Exception {
        super.setUp(browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
}

    @Test
    public void testSuccessfulLogin() {
        logger.info("Starting successful login test");

        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-login")));

        usernameField.sendKeys("test1");
        passwordField.sendKeys("test1");
        loginButton.click();

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h2")));

        try {
            wait.until(ExpectedConditions.textToBe(By.tagName("h2"), "Login Successful!"));
        } catch (Exception e) {
            logger.warning("Expected text not found. Current text: " + successMessage.getText());
        }

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("login_successful.png"));
            logger.info("Screenshot saved as login_successful.png");
        } catch (Exception e) {
            logger.warning("Failed to save screenshot: " + e.getMessage());
        }

        logger.info("Page source after login: " + driver.getPageSource());

        Assert.assertEquals(successMessage.getText(), "Login Successful!", "Login success message is incorrect");
        logger.info("Successful login test completed");
    }
}
