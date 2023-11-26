package tests.global.footer;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import test_flows.global.FooterTestFlow;

public class FooterTest {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        try {
            driver.get("https://demowebshop.tricentis.com/");
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyFooterComponent();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }

    }
}
