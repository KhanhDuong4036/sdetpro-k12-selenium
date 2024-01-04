package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import test_data.CreditCardType;

@ComponentCSSSelector("#opc-payment_info")
public class PaymentInformationComponent extends Component {

    private static final By creditCardDropdownSel = By.id("CreditCardType");
    private static final By cardHolderNameSel = By.id("CardholderName");
    private static final By cardNumberSel = By.id("CardNumber");
    private static final By cardExpireMonthDropdownSel = By.id("ExpireMonth");
    private static final By cardExpireYearDropdownSel = By.id("ExpireYear");
    private static final By cardCodeSel = By.id("CardCode");
    private static final By purchaseOrderNumberSel = By.id("PurchaseOrderNumber");

    private static final By paymentInfoSectionSel = By.xpath("//div[@class='section payment-info']//tbody//tr//td/p");
    private static final By continueBtnSel = By.cssSelector("input[class*='payment-info-next-step-button']");


    public PaymentInformationComponent(WebDriver driver, WebElement component) {
        super(driver, component);

    }

    public void selectCardType(CreditCardType creditCardType) {
        Assert.assertNotNull(creditCardType, "[ERR] Credit Card type can't be null");
        Select select = new Select(findElement(creditCardDropdownSel));
        switch (creditCardType) {
            case VISA:
                select.selectByVisibleText("Visa");
                break;
            case MASTER_CARD:
                select.selectByVisibleText("Master card");
                break;
            case DISCOVER:
                select.selectByVisibleText("Discover");
                break;
            case AMEX:
                select.selectByVisibleText("Amex");
                break;
        }
    }

    public void inputCardHolderName(String name){
        findElement(cardHolderNameSel).sendKeys(name);
    }

    public void inputCardNumber(String cardNumber){
        findElement(cardNumberSel).sendKeys(cardNumber);
    }

    public void inputExpiredMonth(String month){
        Select select = new Select(findElement(cardExpireMonthDropdownSel));
        select.selectByVisibleText(month);
    }

    public void inputExpiredYear(String year){
        Select select = new Select(findElement(cardExpireYearDropdownSel));
        select.selectByVisibleText(year);
    }

    public void inputCardCode(String cardCode){
        findElement(cardCodeSel).sendKeys(cardCode);
    }

    public void inputPurchaseNumber(String purchaseNumber){
        findElement(purchaseOrderNumberSel).sendKeys(purchaseNumber);
    }

    public String getPaymentInfoSectionText(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentInfoSectionSel));
        return findElement(paymentInfoSectionSel).getText();
    }

    public void clickContinueBtn() {
        findElement(continueBtnSel).click();
    }


}
