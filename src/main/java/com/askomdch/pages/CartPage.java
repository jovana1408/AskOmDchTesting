package com.askomdch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartItems       = By.cssSelector("tr.cart_item");
    private final By itemSubtotal    = By.cssSelector("td.product-subtotal .woocommerce-Price-amount bdi");
    private final By cartSubtotal    = By.cssSelector("tr.cart-subtotal td .woocommerce-Price-amount bdi");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public double getCalculatedTotal() {
        List<WebElement> subtotals = driver.findElements(itemSubtotal);
        double total = 0.0;
        for (WebElement el : subtotals) {
            String text = el.getText().replace("$", "").replace(",", "").trim();
            total += Double.parseDouble(text);
        }
        return total;
    }

    public double getDisplayedSubtotal() {
        String text = waitForElement(cartSubtotal).getText()
                .replace("$", "").replace(",", "").trim();
        return Double.parseDouble(text);
    }
}