package com.askomdch.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    protected WebDriver driver;
    protected static final String BASE_URL = "https://askomdch.com";

    // Test podaci
    protected static final String USER_USERNAME = "testuser123";
    protected static final String USER_EMAIL    = "testuser123@gmail.com";
    protected static final String USER_PASS     = "Test1234!";

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    protected void clearCart() {
        driver.get(BASE_URL + "/cart/");
        try { Thread.sleep(1000); } catch (Exception e) {}

        while (true) {
            List<org.openqa.selenium.WebElement> removeButtons =
                    driver.findElements(By.cssSelector("a.remove"));
            if (removeButtons.isEmpty()) break;

            String removeUrl = removeButtons.get(0).getAttribute("href");
            driver.get(removeUrl);
            try { Thread.sleep(1000); } catch (Exception e) {}
        }
    }
}