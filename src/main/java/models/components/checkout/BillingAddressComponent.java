package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

@ComponentCSSSelector("#opc-billing")
public class BillingAddressComponent extends Component {

    private static final By inputAddressDropdownSel = By.id("billing-address-select");
    private static final By firstNameSel = By.id("BillingNewAddress_FirstName");
    private static final By lastNameSel = By.id("BillingNewAddress_LastName");
    private static final By emailSel = By.id("BillingNewAddress_Email");
    private static final By selectCountryDropdownSel = By.id("BillingNewAddress_CountryId");
    private static final By loadingStateProgressBarSel = By.id("states-loading-progress");
    private static final By selectStatesDropdownSel = By.id("BillingNewAddress_StateProvinceId");
    private static final By citySel = By.id("BillingNewAddress_City");
    private static final By address01Sel = By.id("BillingNewAddress_Address1");
    private static final By address02Sel = By.id("BillingNewAddress_Address2");
    private static final By zipCodeSel = By.id("BillingNewAddress_ZipPostalCode");
    private static final By phoneNumberSel = By.id("BillingNewAddress_PhoneNumber");
    private static final By continueBtnSel = By.cssSelector("input[class*='button-1 new-address-next-step-button']");

    public BillingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);

    }

    public void selectInputNewAddress(){
        if(!findElements(inputAddressDropdownSel).isEmpty()){
            Select select = new Select(findElement(inputAddressDropdownSel));
            select.selectByVisibleText("New Address");

        }
    }

    public void inputFirstName(String firstName) {
        findElement(firstNameSel).sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        findElement(lastNameSel).sendKeys(lastName);
    }

    public void inputEmail(String email) {
        findElement(emailSel).sendKeys(email);
    }

    public void selectCountry(String country) {
        Select select = new Select(findElement(selectCountryDropdownSel));
        select.selectByVisibleText(country);
        wait.until(ExpectedConditions.invisibilityOf(findElement(loadingStateProgressBarSel)));
    }

    public void selectState(String state) {
        Select select = new Select(findElement(selectStatesDropdownSel));
        select.selectByVisibleText(state);
    }

    public void inputCity(String city) {
        findElement(citySel).sendKeys(city);
    }

    public void inputAddress01(String add01) {
        findElement(address01Sel).sendKeys(add01);
    }

    public void inputAddress02(String add02) {
        findElement(address02Sel).sendKeys(add02);
    }

    public void inputZipCode(String zipCode) {
        findElement(zipCodeSel).sendKeys(zipCode);
    }

    public void inputPhoneNumber(String phoneNumber) {
        findElement(phoneNumberSel).sendKeys(phoneNumber);
    }

    public void clickContinueBtn() {
        findElement(continueBtnSel).click();
        wait.until(ExpectedConditions.invisibilityOf(findElement(continueBtnSel)));
    }


}
