package com.askomdch.tests;

import com.askomdch.pages.LoginPage;
import com.askomdch.pages.AccountPage;
import com.askomdch.utils.LoggerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProfileDataTest extends BaseTest {

    @Test
    public void testProfileData() {
        // Prvo se prijavi
        driver.get(BASE_URL + "/account/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUsername(USER_USERNAME);
        loginPage.fillPassword(USER_PASS);
        loginPage.clickLogin();

        // Idi na Edit Account stranicu
        driver.get(BASE_URL + "/account/edit-account/");

        AccountPage accountPage = new AccountPage(driver);
        String actualUsername = accountPage.getDisplayName();
        String actualEmail    = accountPage.getEmail();

        boolean userMatch  = USER_USERNAME.equals(actualUsername);
        boolean emailMatch = USER_EMAIL.equals(actualEmail);
        boolean passed     = userMatch && emailMatch;

        LoggerUtil.logInfo("Ocekivani username: " + USER_USERNAME +
                " | Pronadjen: " + actualUsername);
        LoggerUtil.logInfo("Ocekivani email: " + USER_EMAIL +
                " | Pronadjen: " + actualEmail);
        LoggerUtil.log(
                "ProfileDataTest",
                "Provera da li podaci profila odgovaraju podacima registracije",
                passed
        );

        Assertions.assertTrue(passed,
                "Podaci profila se ne poklapaju sa podacima registracije!");
    }
}
