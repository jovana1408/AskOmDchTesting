package com.askomdch.tests;

import com.askomdch.pages.LoginPage;
import com.askomdch.utils.LoggerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() {

        // Prvo se prijavi
        driver.get(BASE_URL + "/account/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsername(USER_USERNAME);
        loginPage.fillPassword(USER_PASS);
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Klikni na logout link
        WebElement logoutLink = wait.until(ExpectedConditions
                .presenceOfElementLocated(
                        By.cssSelector("a[href*='customer-logout']")));
        logoutLink.click();

        // Proveri da li smo odjavljeni - treba da se pojavi login forma
        boolean passed = false;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("username")));
            passed = true;
        } catch (Exception e) {
            passed = false;
        }

        LoggerUtil.log(
                "LogoutTest",
                "Odjavljivanje korisnika sa naloga",
                passed
        );

        Assertions.assertTrue(passed,
                "Odjavljivanje nije uspelo!");
    }
}
