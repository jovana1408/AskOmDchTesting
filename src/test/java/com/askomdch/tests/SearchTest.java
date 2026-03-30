package com.askomdch.tests;

import com.askomdch.utils.LoggerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchTest extends BaseTest {

    @Test
    public void testSearch() {
        driver.get(BASE_URL + "/store/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Unesi pojam za pretragu
        WebElement searchField = wait.until(ExpectedConditions
                .presenceOfElementLocated(
                        By.id("woocommerce-product-search-field-0")));
        searchField.clear();
        searchField.sendKeys("jeans");

        // Klikni Search dugme
        driver.findElement(By.cssSelector(
                "button[type='submit']")).click();

        // Sacekaj rezultate
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("li.product")));

        // Proveri da li ima rezultata
        List<WebElement> results = driver.findElements(
                By.cssSelector("li.product"));
        boolean passed = !results.isEmpty();

        LoggerUtil.log(
                "SearchTest",
                "Pretraga proizvoda po kljucnoj reci 'jeans'",
                passed
        );
        LoggerUtil.logInfo("Broj pronadjenih proizvoda: " + results.size());

        Assertions.assertTrue(passed,
                "Pretraga nije vratila rezultate!");
    }
}
