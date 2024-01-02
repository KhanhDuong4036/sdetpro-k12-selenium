package models.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOptionPage extends BasePage{

    private static final By checkoutAsGuestBtnSel =By.cssSelector("input[class*='checkout-as-guest-button']");

    public CheckoutOptionPage(WebDriver driver) {
        super(driver);
    }

    public void checkoutAsGuest(){
        findElement(checkoutAsGuestBtnSel).click();
    }

}
