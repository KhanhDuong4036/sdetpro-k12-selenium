package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@ComponentCSSSelector("#opc-shipping_method")
public class ShippingMethodComponent extends Component {

    private static final By continueBtnSel = By.cssSelector("input[class*='shipping-method-next-step-button']");

    public ShippingMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);

    }

    public void selectShippingMethod(String method){
        findElement(By.xpath("//label[contains(text(), '" + method + "')]")).click();
    }

    public void clickContinueBtn() {
        findElement(continueBtnSel).click();
        wait.until(ExpectedConditions.invisibilityOf(findElement(continueBtnSel)));
    }


}
