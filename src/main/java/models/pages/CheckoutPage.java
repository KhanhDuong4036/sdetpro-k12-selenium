package models.pages;

import models.components.checkout.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CheckoutPage extends BasePage{

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public BillingAddressComponent billingAddressComponent(){
        return findComponent(BillingAddressComponent.class);
    }

    public ShippingAddressComponent shippingAddressComponent(){
        return findComponent(ShippingAddressComponent.class);
    }

    public ShippingMethodComponent shippingMethodComponent(){
        return findComponent(ShippingMethodComponent.class);
    }

    public PaymentMethodComponent paymentMethodComponent(){
        return findComponent(PaymentMethodComponent.class);
    }

    public PaymentInformationComponent paymentInformationComponent(){
        return findComponent(PaymentInformationComponent.class);
    }
    public ConfirmOrderComponent confirmOrderComponent(){
        return findComponent(ConfirmOrderComponent.class);
    }


}
