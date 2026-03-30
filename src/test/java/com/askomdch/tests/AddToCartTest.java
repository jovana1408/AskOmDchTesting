package com.askomdch.tests;

import com.askomdch.pages.CartPage;
import com.askomdch.pages.LoginPage;
import com.askomdch.utils.LoggerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddToCartTest extends BaseTest {

    @Test
    public void testAddToCart() {

        // Prijava
        driver.get(BASE_URL + "/account/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsername(USER_USERNAME);
        loginPage.fillPassword(USER_PASS);
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Ocisti korpu pre testa
        clearCart();

        // Proizvod 1 - kategorija Men
        driver.get(BASE_URL + "/product-category/men/");
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.add_to_cart_button")));
        driver.findElements(By.cssSelector("a.add_to_cart_button")).get(0).click();
        try { Thread.sleep(1500); } catch (Exception e) {}

        // Proizvod 2 - kategorija Women
        driver.get(BASE_URL + "/product-category/women/");
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.add_to_cart_button")));
        driver.findElements(By.cssSelector("a.add_to_cart_button")).get(0).click();
        try { Thread.sleep(1500); } catch (Exception e) {}

        // Proizvod 3 - kategorija Accessories (get(1) - drugi proizvod)
        driver.get(BASE_URL + "/product-category/accessories/");
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.add_to_cart_button")));
        driver.findElements(By.cssSelector("a.add_to_cart_button")).get(1).click();
        try { Thread.sleep(1500); } catch (Exception e) {}

        // Idi na korpu i proveri broj proizvoda
        driver.get(BASE_URL + "/cart/");
        CartPage cartPage = new CartPage(driver);

        int itemCount = cartPage.getCartItemCount();
        boolean passed = itemCount >= 3;

        LoggerUtil.log(
                "AddToCartTest",
                "Dodavanje 3 proizvoda iz razlicitih kategorija (Men, Women, Accessories)",
                passed
        );
        LoggerUtil.logInfo("Broj proizvoda u korpi: " + itemCount);

        Assertions.assertTrue(passed,
                "Nisu dodata 3 proizvoda u korpu! Broj: " + itemCount);
    }
}