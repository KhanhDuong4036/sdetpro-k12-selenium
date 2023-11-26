package tests;

import driver.DriverFactory;
import models.pages.LoginPageModel03;
import org.openqa.selenium.WebDriver;

public class POMModel03Tests {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get("");
        LoginPageModel03 loginPage = new LoginPageModel03(driver);
        loginPage
                .inputUsername("khanhduong4036")
                .inputPassword("hanhle4036")
                .clickLoginBtn();

    }
}
