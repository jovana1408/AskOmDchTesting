package com.askomdch.tests;

import com.askomdch.utils.LoggerUtil;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        RegistrationTest.class,
        LoginTest.class,
        ProfileDataTest.class,
        AddToCartTest.class,
        CartPriceTest.class,
        PageLoadPerformanceTest.class,
        CompanyInfoTest.class,
        SearchTest.class,
        SortByPriceTest.class,
        LogoutTest.class,
        EmptyCartTest.class
})
public class DemoSuiteTest {

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LoggerUtil.logInfo("Svi testovi su izvrseni.");
            LoggerUtil.close();
        }));
    }
}
