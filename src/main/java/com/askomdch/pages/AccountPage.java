package com.askomdch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {

    private final By displayNameField = By.id("account_display_name");
    private final By emailField       = By.id("account_email");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public String getDisplayName() {
        return waitForElement(displayNameField).getAttribute("value");
    }

    public String getEmail() {
        return waitForElement(emailField).getAttribute("value");
    }
}
