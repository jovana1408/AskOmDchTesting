package com.askomdch.tests;

import com.askomdch.pages.LoginPage;
import com.askomdch.utils.LoggerUtil;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        driver.get(BASE_URL + "/account/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsername(USER_USERNAME);
        loginPage.fillPassword(USER_PASS);
        loginPage.clickLogin();

        boolean passed = loginPage.isLoginSuccessful();
        LoggerUtil.log(
                "LoginTest",
                "Prijava korisnika: " + USER_USERNAME,
                passed
        );

        org.junit.jupiter.api.Assertions.assertTrue(
                passed, "Prijava nije uspela!");
    }
}
