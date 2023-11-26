package tests;

import driver.DriverFactory;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class ProductsComponentTest {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get("https://demowebshop.tricentis.com/");
        try{
            HomePage homePage = new HomePage(driver);
            homePage.productItemComponentList().forEach(productItemComponent -> {
                System.out.println(productItemComponent.productTitleEle().getText());
            });
        }catch (Exception ignored){

        }finally {
            driver.quit();
        }

    }

}
