package com.askomdch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement el = waitForElement(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected void scrollToElement(By locator) {
        WebElement el = waitForElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", el);
    }

    protected void jsClick(By locator) {
        WebElement el = waitForElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", el);
    }

    protected boolean isElementPresent(By locator) {
        try {
            waitForElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}