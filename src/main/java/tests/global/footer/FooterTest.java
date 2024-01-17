package tests.global.footer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test_flows.global.FooterTestFlow;
import tests.BaseTest;

public class FooterTest extends BaseTest {

    @Test
    public void testHomePageFooter() {
        WebDriver driver = getDriver();
        driver.get("https://demowebshop.tricentis.com/");
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }
    @Test
    public void testCategoryPageFooter() {
        WebDriver driver = getDriver();
        driver.get("https://demowebshop.tricentis.com/");
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCateFooterComponent();
    }
}
