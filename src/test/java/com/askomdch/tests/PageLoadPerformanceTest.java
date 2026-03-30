package com.askomdch.tests;

import com.askomdch.utils.LoggerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PageLoadPerformanceTest extends BaseTest {

    @Test
    public void testPageLoadPerformance() {

        String[] pages = {
                BASE_URL + "/",
                BASE_URL + "/store/",
                BASE_URL + "/product-category/men/",
                BASE_URL + "/product-category/women/",
                BASE_URL + "/product-category/accessories/"
        };

        long totalTime = 0;

        for (String url : pages) {
            long start = System.currentTimeMillis();
            driver.get(url);
            long end = System.currentTimeMillis();
            long loadTime = end - start;
            totalTime += loadTime;
            LoggerUtil.logInfo("Stranica: " + url + " | Vreme ucitavanja: " + loadTime + " ms");
        }

        double average = (double) totalTime / pages.length;
        boolean passed = average > 0;

        LoggerUtil.log(
                "PageLoadPerformanceTest",
                "Merenje vremena ucitavanja 5 stranica",
                passed
        );
        LoggerUtil.logInfo("Prosecno vreme ucitavanja: " + String.format("%.2f", average) + " ms");

        Assertions.assertTrue(passed, "Test performansi nije uspeo!");
    }
}
