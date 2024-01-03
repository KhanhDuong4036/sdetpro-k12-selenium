package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

@ComponentCSSSelector("#opc-shipping")
public class ShippingAddressComponent extends Component {

    private static final By continueBtnSel = By.cssSelector("input[class*='new-address-next-step-button']");

    public ShippingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);

    }

    public void clickContinueBtn() {
        findElement(continueBtnSel).click();
    }


}
