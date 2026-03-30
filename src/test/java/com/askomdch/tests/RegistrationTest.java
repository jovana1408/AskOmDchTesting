package com.askomdch.tests;

import com.askomdch.pages.RegisterPage;
import com.askomdch.utils.LoggerUtil;
import org.junit.jupiter.api.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void testRegistration() {
        driver.get(BASE_URL + "/account/");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillUsername(USER_USERNAME);
        registerPage.fillEmail(USER_EMAIL);
        registerPage.fillPassword(USER_PASS);
        registerPage.clickRegister();

        boolean passed = registerPage.isRegistrationSuccessful();
        LoggerUtil.log(
                "RegistrationTest",
                "Registracija novog korisnika: " + USER_USERNAME,
                passed
        );

        org.junit.jupiter.api.Assertions.assertTrue(
                passed, "Registracija nije uspela!");
    }
}
