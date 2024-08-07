package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.time.Duration;
import java.util.logging.Logger;

public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = Logger.getLogger(BaseTest.class.getName());

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) throws Exception {
        logger.info("Setting up WebDriver for browser: " + browser);
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        
        String remoteUrl = System.getProperty("selenium.grid.url", "http://localhost:4444/wd/hub");
        logger.info("Connecting to Selenium Grid at: " + remoteUrl);
        
        try {
            driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
            logger.info("WebDriver created successfully");
        } catch (Exception e) {
            logger.severe("Failed to create WebDriver: " + e.getMessage());
            throw e;
        }

        String appUrl = System.getProperty("app.url", "http://host.docker.internal:8888/login");
        logger.info("Navigating to application URL: " + appUrl);
        try {
            driver.get(appUrl);
            logger.info("Successfully navigated to application URL");
        } catch (Exception e) {
            logger.severe("Failed to navigate to application URL: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Quitting WebDriver");
            try {
                driver.quit();
            } catch (Exception e) {
                logger.warning("Error while quitting WebDriver: " + e.getMessage());
            }
        }
    }
}
