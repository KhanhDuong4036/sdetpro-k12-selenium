package models.components.order;

import io.qameta.allure.Step;
import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseItemComponent extends Component {

    private  static final By productPriceSel = By.cssSelector(".product-price");
    private  static final By addToCartBtn = By.cssSelector("input[id^='add-to-cart-button']");

    private static final By productQuantitySel = By.cssSelector(".add-to-cart input[class^='qty-input']");
    private static final By barNotificationSel = By.id("bar-notification");


    public BaseItemComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Step("Getting product price")
    public double productPrice(){
        String productPriceStr = findElement(productPriceSel).getText().trim();
        return Double.parseDouble(productPriceStr);
    }
    @Step("Click on cart button")
    public void clickOnAddToCartBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        findElement(addToCartBtn).click();
    }
    @Step("Buying with quantity : {quantity}")
    public void setProductQuantity(int quantity){
        findElement(productQuantitySel).clear();
        findElement(productQuantitySel).sendKeys(String.valueOf(quantity));
    }
    @Step("Wait until items added to cart")
    public void waitUntilItemAddedToCart(){
        String successMessage = "The product has been added to your shopping cart";
        wait.until(ExpectedConditions.textToBePresentInElementLocated(barNotificationSel, successMessage));
    }

}
