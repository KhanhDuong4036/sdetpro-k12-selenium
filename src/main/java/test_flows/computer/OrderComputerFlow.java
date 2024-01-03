package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.BillingAddressComponent;
import models.components.checkout.PaymentInformationComponent;
import models.components.checkout.PaymentMethodComponent;
import models.components.checkout.ShippingMethodComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckoutOptionPage;
import models.pages.CheckoutPage;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.CreditCardType;
import test_data.DataObjectBuilder;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_data.user.UserDataObject;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private Class<T> computerEssentialCompClass;
    private WebDriver driver;
    private ComputerData computerData;
    private double itemTotalPrice;
    private int quantity;
    private UserDataObject defaultCheckoutUser;
    private PaymentMethod paymentMethod;
    private CreditCardType creditCardType;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialCompClass, ComputerData computerData) {
        this.computerEssentialCompClass = computerEssentialCompClass;
        this.driver = driver;
        this.computerData = computerData;
        this.quantity = 1;
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialCompClass, ComputerData computerData, int quantity) {
        this.computerEssentialCompClass = computerEssentialCompClass;
        this.driver = driver;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildComputerSpec() {
        ComputerEssentialComponent computerEssentialComponent = new ComputerItemDetailsPage(driver).computerComp(computerEssentialCompClass);
        computerEssentialComponent.unselectDefaultOptions();
        String processorFullStr = computerEssentialComponent.selectProcessorType(this.computerData.getProcessor());
        double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);
        String ramFullStr = computerEssentialComponent.selectRAMType(this.computerData.getRam());
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);
        String hddFullStr = computerEssentialComponent.selectHDD(this.computerData.getHdd());
        double hddAdditionalPrice = extractAdditionalPrice(hddFullStr);
        String softwareFullStr = computerEssentialComponent.selectSoftware(this.computerData.getSoftware());
        double softwareAdditionalPrice = extractAdditionalPrice(softwareFullStr);
        computerEssentialComponent.clickOnAddToCartBtn();
        double osAdditionalPrice = 0;
        String osOption = this.computerData.getOs();
        if (osOption != null) {
            String osFullStr = computerEssentialComponent.selectOS(osOption);
            osAdditionalPrice = extractAdditionalPrice(osFullStr);
        }

        // Set item quantity
        computerEssentialComponent.setProductQuantity(this.quantity);
        double additionalPrice = processorAdditionalPrice + ramAdditionalPrice + hddAdditionalPrice + softwareAdditionalPrice + osAdditionalPrice;
        this.itemTotalPrice = (computerEssentialComponent.productPrice() + additionalPrice) * this.quantity;

    }

    public void addItemToCart() {
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        ComputerEssentialComponent computerEssentialComponent = new ComputerItemDetailsPage(driver).computerComp(computerEssentialCompClass);
        computerEssentialComponent.clickOnAddToCartBtn();
        computerEssentialComponent.waitUntilItemAddedToCart();
        computerItemDetailsPage.headerComp().clickOnShoppingCartLink();

    }

    public void verifyShoppingCartPage() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowComponents = shoppingCartPage.cartItemRowComponents();
        // Verify shopping cart details
        Assert.assertFalse(cartItemRowComponents.isEmpty(), "[ERR] There is no items displayed in the shopping cart!!!");
        double currentSubTotals = 0;
        double currentTotalUnitPrices = 0;
        for (CartItemRowComponent cartItemRowComponent : cartItemRowComponents) {
            currentSubTotals = currentSubTotals + cartItemRowComponent.subTotal();
            currentTotalUnitPrices = currentTotalUnitPrices + (cartItemRowComponent.itemQuantity() * cartItemRowComponent.unitPrice());
        }
        Assert.assertEquals(currentSubTotals, currentTotalUnitPrices, "[ERR] Shopping cart sub-total is incorrect!");
        System.out.println("Current Sub Totals: " + currentSubTotals);
        System.out.println("Current Total Unit Price: " + currentTotalUnitPrices);

        // Verify checkout prices
        TotalComponent totalComponent = shoppingCartPage.totalComponent();
        Map<String, Double> priceCategories = totalComponent.priceCategories();
        Assert.assertFalse(priceCategories.keySet().isEmpty(), "[ERR] Checkout price info is empty!!!");
        double checkoutSubTotal = 0;
        double checkoutTotal = 0;
        double checkoutOtherFees = 0;
        for (String priceType : priceCategories.keySet()) {
            double priceValue = priceCategories.get(priceType);
            if (priceType.startsWith("Sub-Total")) {
                checkoutSubTotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFees = checkoutOtherFees + priceValue;
            }
        }
        Assert.assertEquals(checkoutSubTotal, currentSubTotals, "[ERR] Checkout sub-total is incorrect");
        Assert.assertEquals(checkoutTotal, (checkoutSubTotal + checkoutOtherFees), "[ERR] Checkout Total is incorrect");
    }

    public void agreeTOSAndCheckout() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        TotalComponent totalComponent = shoppingCartPage.totalComponent();
        totalComponent.agreeTOS();
        totalComponent.clickOnCheckoutBtn();

        // This is exception case, please do not use one flow method for more than one page
        new CheckoutOptionPage(driver).checkoutAsGuest();
    }

    public void inputBillingAddress() {
        String defaultCheckoutUserDataLOC = "/src/main/java/test_data/user/DefaultCheckoutUser.json";
        this.defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserDataLOC, UserDataObject.class);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        BillingAddressComponent billingAddressComponent = checkoutPage.billingAddressComponent();
        billingAddressComponent.selectInputNewAddress();
        billingAddressComponent.inputFirstName(defaultCheckoutUser.getFirstName());
        billingAddressComponent.inputLastName(defaultCheckoutUser.getLastName());
        billingAddressComponent.inputEmail(defaultCheckoutUser.getEmail());
        billingAddressComponent.selectCountry(defaultCheckoutUser.getCountry());
        billingAddressComponent.selectState(defaultCheckoutUser.getState());
        billingAddressComponent.inputCity(defaultCheckoutUser.getCity());
        billingAddressComponent.inputAddress01(defaultCheckoutUser.getAddress01());
        billingAddressComponent.inputAddress02(defaultCheckoutUser.getAddress02());
        billingAddressComponent.inputZipCode(defaultCheckoutUser.getZipcode());
        billingAddressComponent.inputPhoneNumber(defaultCheckoutUser.getPhoneNumber());
        billingAddressComponent.clickContinueBtn();
    }

    public void inputShippingAddress() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.shippingAddressComponent().clickContinueBtn();
    }

    public void selectShippingMethod() {
        List<String> shippingMethods = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        int randomShippingMethodIndex = new SecureRandom().nextInt(shippingMethods.size());
        String randomShippingMethod = shippingMethods.get(randomShippingMethodIndex);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ShippingMethodComponent shippingMethodComponent = checkoutPage.shippingMethodComponent();
        shippingMethodComponent.selectShippingMethod(randomShippingMethod);
        shippingMethodComponent.clickContinueBtn();
    }

    public void selectPaymentMethod() {
        this.paymentMethod = PaymentMethod.COD;
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentMethodComponent paymentMethodComponent = checkoutPage.paymentMethodComponent();
        paymentMethodComponent.selectCODMethod();
        paymentMethodComponent.clickContinueBtn();
    }

    public void selectPaymentMethod(PaymentMethod paymentMethod) {
        Assert.assertNotNull(paymentMethod, "[ERR] Payment Method Type can't be null");
        this.paymentMethod = paymentMethod;
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentMethodComponent paymentMethodComponent = checkoutPage.paymentMethodComponent();
        switch (paymentMethod) {
            case CHECK_MONEY_ORDER:
                paymentMethodComponent.selectCheckMoneyOderMethod();
                break;
            case CREDIT_CARD:
                paymentMethodComponent.selectCreditCardMethod();
                break;
            case PURCHASE_ORDER:
                paymentMethodComponent.purchaseOrderMethod();
                break;
            default:
                paymentMethodComponent.selectCODMethod();
        }
        paymentMethodComponent.clickContinueBtn();

    }

//    public void verifyCODandCheckMoneyOrderPaymentInfo(){
//        CheckoutPage checkoutPage = new CheckoutPage(driver);
//        PaymentInformationComponent paymentInformationComponent = checkoutPage.paymentInformationComponent();
//        if(this.paymentMethod.equals(PaymentMethod.COD)){
//            String codText = paymentInformationComponent.getPaymentInfoSectionText();
//            Assert.assertEquals(codText, "You will pay by COD");
//
//        } else if (this.paymentMethod.equals(PaymentMethod.CHECK_MONEY_ORDER)){
//            String checkMoneyOrderText = paymentInformationComponent.getPaymentInfoSectionText();
//            Assert.assertTrue(checkMoneyOrderText.contains("Mail Personal or Business Check, Cashier's Check or money order to:"));
//            System.out.println(checkMoneyOrderText);
//        }
//    }

    public void inputPaymentInfo(CreditCardType creditCardType){
        this.creditCardType = creditCardType;
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PaymentInformationComponent paymentInformationComponent = checkoutPage.paymentInformationComponent();
        if (this.paymentMethod.equals(PaymentMethod.PURCHASE_ORDER)){
            paymentInformationComponent.inputPurchaseNumber("123");
        } else if (this.paymentMethod.equals(PaymentMethod.CREDIT_CARD)){
            paymentInformationComponent.selectCardType(creditCardType);
            String cardHolderFirstName = this.defaultCheckoutUser.getFirstName();
            String cardHolderLastName = this.defaultCheckoutUser.getLastName();
            paymentInformationComponent.inputCardHolderName(cardHolderFirstName + " " + cardHolderLastName);
            String cardNumber = creditCardType.equals(CreditCardType.VISA) ? "4111111111111111" : "6011000990139424";
            paymentInformationComponent.inputCardNumber(cardNumber);

            // Current month and next year
            Calendar calendar = new GregorianCalendar();
//            paymentInformationComponent.inputExpiredMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
            paymentInformationComponent.inputExpiredMonth("11");
//            paymentInformationComponent.inputExpiredYear(String.valueOf(calendar.get(Calendar.YEAR) + 1));
            paymentInformationComponent.inputExpiredYear("2025");
            paymentInformationComponent.inputCardCode("123");
            paymentInformationComponent.clickContinueBtn();
        } else if(this.paymentMethod.equals(PaymentMethod.COD)){
//            String codText = paymentInformationComponent.getPaymentInfoSectionText();
//            Assert.assertEquals(codText, "You will pay by COD");
//            System.out.println(codText);
        } else {
//            // Verify the text is "Mail Personal or Business Check, Cashier's Check or money order to:"
//            String checkMoneyOrderText = paymentInformationComponent.getPaymentInfoSectionText();
//            Assert.assertTrue(checkMoneyOrderText.contains("Mail Personal or Business Check, Cashier's Check or money order to:"));
//            System.out.println(checkMoneyOrderText);
        }
    }

    public void confirmOrder(){
        new CheckoutPage(driver).confirmOrderComponent().clickOnConfirmOrderBtn();
    }


    private double extractAdditionalPrice(String optionStr) {
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(optionStr);
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1).replaceAll("[+-]", ""));
        }
        return price;
    }


}
