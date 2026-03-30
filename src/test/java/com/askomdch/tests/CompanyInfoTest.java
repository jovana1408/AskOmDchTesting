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

public class CompanyInfoTest extends BaseTest {

    @Test
    public void testCompanyInfo() {
        driver.get(BASE_URL + "/about/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Sacekaj da se ucita naslov About Us
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("h3")));

        // Prikupi naziv kompanije
        String companyName = driver.getTitle();

        // Prikupi tekst naslova
        WebElement heading = driver.findElement(By.cssSelector(
                ".wp-block-media-text__content h3"));
        String headingText = heading.getText();

        // Prikupi sve paragrafe sa opisom
        List<WebElement> paragraphs = driver.findElements(By.cssSelector(
                ".wp-block-media-text__content p"));
        StringBuilder companyInfo = new StringBuilder();
        for (WebElement p : paragraphs) {
            String text = p.getText().trim();
            if (!text.isEmpty()) {
                companyInfo.append(text).append("\n");
            }
        }

        boolean passed = !headingText.isEmpty() && companyInfo.length() > 0;

        // Stampaj podatke o kompaniji u izvestaj
        LoggerUtil.logInfo("=========== PODACI O KOMPANIJI ===========");
        LoggerUtil.logInfo("Naziv sajta:  " + companyName);
        LoggerUtil.logInfo("Naslov:       " + headingText);
        LoggerUtil.logInfo("Opis:         " + companyInfo.toString().trim());
        LoggerUtil.logInfo("URL:          " + BASE_URL + "/about/");
        LoggerUtil.logInfo("==========================================");

        LoggerUtil.log(
                "CompanyInfoTest",
                "Prikupljanje podataka o kompaniji sa About stranice",
                passed
        );

        Assertions.assertTrue(passed,
                "Nisu pronadjeni podaci o kompaniji!");
    }
}
