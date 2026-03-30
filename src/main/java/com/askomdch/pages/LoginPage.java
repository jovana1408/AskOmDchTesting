package com.askomdch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginBtn      = By.cssSelector(
            "button.woocommerce-form-login__submit");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillUsername(String username) {
        type(usernameField, username);
    }

    public void fillPassword(String password) {
        type(passwordField, password);
    }

    public void clickLogin() {
        click(loginBtn);
    }

    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.urlContains("/account/"));
            return isElementPresent(By.cssSelector("a[href*='customer-logout']"));
        } catch (Exception e) {
            return false;
        }
    }
    }

