package com.askomdch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private final By usernameField = By.id("reg_username");
    private final By emailField    = By.id("reg_email");
    private final By passwordField = By.id("reg_password");
    private final By registerBtn   = By.cssSelector(
            "button.woocommerce-form-register__submit");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void fillUsername(String username) {
        type(usernameField, username);
    }

    public void fillEmail(String email) {
        type(emailField, email);
    }

    public void fillPassword(String password) {
        type(passwordField, password);
    }

    public void clickRegister() {
        scrollToElement(registerBtn);
        click(registerBtn);
    }

    public boolean isRegistrationSuccessful() {
        try {
            Thread.sleep(3000);
            String url = driver.getCurrentUrl();
            return url.equals("https://askomdch.com/account/") &&
                    !url.contains("register");
        } catch (Exception e) {
            return false;
        }
    }
}
