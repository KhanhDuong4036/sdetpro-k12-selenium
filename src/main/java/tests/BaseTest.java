package tests;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeTest
    protected void initBrowserSession(){
        driver = DriverFactory.getWebDriver();
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowserSession(){
        if(driver != null){
        driver.quit();
        }
    }
}
