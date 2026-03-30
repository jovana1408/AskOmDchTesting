package com.askomdch.tests;

import com.askomdch.pages.CartPage;
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

public class CartPriceTest extends BaseTest {

    @Test
    public void testCartPrice() {

        // Prijava
        driver.get(BASE_URL + "/account/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsername(USER_USERNAME);
        loginPage.fillPassword(USER_PASS);
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
// Ocisti korpu pre testa
        clearCart();
        // Dodaj proizvode iz 3 kategorije
        driver.get(BASE_URL + "/product-category/men/");
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.add_to_cart_button")));
        driver.findElements(By.cssSelector("a.add_to_cart_button")).get(0).click();

        driver.get(BASE_URL + "/product-category/women/");
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.add_to_cart_button")));
        driver.findElements(By.cssSelector("a.add_to_cart_button")).get(0).click();

        // Proizvod 3 - kategorija Accessories - uzimamo drugi proizvod
        driver.get(BASE_URL + "/product-category/accessories/");
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("a.add_to_cart_button")));
        driver.findElements(By.cssSelector("a.add_to_cart_button")).get(1).click();

        // Idi na korpu i proveri cenu
        driver.get(BASE_URL + "/cart/");
        CartPage cartPage = new CartPage(driver);

        double calculated = cartPage.getCalculatedTotal();
        double displayed  = cartPage.getDisplayedSubtotal();
        boolean passed    = Math.abs(calculated - displayed) <= 0.01;

        LoggerUtil.log(
                "CartPriceTest",
                "Provera ispravnosti ukupne cene proizvoda u korpi",
                passed
        );
        LoggerUtil.logInfo("Izracunata suma stavki: $" + calculated
                + " | Prikazani Subtotal: $" + displayed);

        Assertions.assertTrue(passed,
                "Cena nije ispravna! Izracunato: " + calculated
                        + ", Prikazano: " + displayed);
    }
}
