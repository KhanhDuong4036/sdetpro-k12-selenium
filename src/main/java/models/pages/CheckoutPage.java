package models.pages;

import models.components.checkout.BillingAddressComponent;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CheckoutPage extends BasePage{

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public BillingAddressComponent billingAddressComponent(){
        return findComponent(BillingAddressComponent.class);
    }


}
