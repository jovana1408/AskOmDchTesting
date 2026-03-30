package com.askomdch.tests;

import com.askomdch.utils.LoggerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SortByPriceTest extends BaseTest {

    @Test
    public void testSortByPrice() {
        driver.get(BASE_URL + "/product-category/men/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Sacekaj da se ucita select
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("select.orderby")));

        // Izaberi sortiranje po ceni
        WebElement sortSelect = driver.findElement(
                By.cssSelector("select.orderby"));
        Select select = new Select(sortSelect);
        select.selectByValue("price");

        // Sacekaj da se stranica osvezi - cekamo da orderby=price bude u URL-u
        wait.until(ExpectedConditions.urlContains("orderby=price"));

        // Sacekaj da se proizvodi ucitaju
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("li.product")));

        // Malo dodatno cekanje da se sve ucita
        try { Thread.sleep(1500); } catch (Exception e) {}

        // Prikupi cene - uzimamo ins tag za cene sa popustom,
// a obican span za cene bez popusta
        List<WebElement> priceElements = driver.findElements(
                By.cssSelector("span.price"));

        List<Double> prices = new ArrayList<>();
        for (WebElement priceEl : priceElements) {
            String text;

            // Ako ima popust, uzmi novu (nizu) cenu iz <ins> taga
            List<WebElement> insEl = priceEl.findElements(By.cssSelector("ins .woocommerce-Price-amount bdi"));
            if (!insEl.isEmpty()) {
                text = insEl.get(0).getText();
            } else {
                // Nema popusta, uzmi obicnu cenu
                List<WebElement> normalEl = priceEl.findElements(By.cssSelector(".woocommerce-Price-amount bdi"));
                if (normalEl.isEmpty()) continue;
                text = normalEl.get(0).getText();
            }

            text = text.replace("$", "").replace(",", "").trim();
            try {
                prices.add(Double.parseDouble(text));
            } catch (NumberFormatException e) {
                // preskoci
            }
        }

        LoggerUtil.logInfo("Pronadjene cene nakon sortiranja: " + prices);

        // Proveri da li su sortirane uzlazno
        boolean passed = true;
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                passed = false;
                LoggerUtil.logInfo("Greska: " + prices.get(i)
                        + " > " + prices.get(i + 1)
                        + " na poziciji " + i);
                break;
            }
        }

        LoggerUtil.log(
                "SortByPriceTest",
                "Sortiranje proizvoda po ceni od najnize ka najvisoj (Men kategorija)",
                passed
        );

        Assertions.assertTrue(passed,
                "Proizvodi nisu sortirani ispravno! Cene: " + prices);
    }
}