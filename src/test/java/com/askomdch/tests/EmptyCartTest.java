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
import java.util.List;

public class EmptyCartTest extends BaseTest {

    @Test
    public void testEmptyCart() {

        // Prijava
        driver.get(BASE_URL + "/account/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsername(USER_USERNAME);
        loginPage.fillPassword(USER_PASS);
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Dodaj jedan proizvod u korpu
        driver.get(BASE_URL + "/product-category/men/");
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.add_to_cart_button")));
        driver.findElements(By.cssSelector("a.add_to_cart_button")).get(0).click();
        try { Thread.sleep(1500); } catch (Exception e) {}

        // Idi na korpu i ukloni sve proizvode navigacijom na href
        driver.get(BASE_URL + "/cart/");

        while (true) {
            List<WebElement> removeButtons = driver.findElements(
                    By.cssSelector("a.remove"));
            if (removeButtons.isEmpty()) break;
            // Navigiramo na remove URL umesto klika - izbegava StaleElement
            String removeUrl = removeButtons.get(0).getAttribute("href");
            driver.get(removeUrl);
            try { Thread.sleep(1000); } catch (Exception e) {}
        }

        // Proveri da li se pojavljuje poruka o praznoj korpi
        boolean passed = false;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("p.cart-empty")));
            String msg = driver.findElement(
                    By.cssSelector("p.cart-empty")).getText();
            passed = msg.contains("Your cart is currently empty");
        } catch (Exception e) {
            passed = false;
        }

        LoggerUtil.log(
                "EmptyCartTest",
                "Uklanjanje svih proizvoda iz korpe i provera da li je korpa prazna",
                passed
        );

        Assertions.assertTrue(passed,
                "Korpa nije prazna nakon uklanjanja svih proizvoda!");
    }
}