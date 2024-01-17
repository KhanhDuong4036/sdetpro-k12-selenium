package tests.order.computer;

import models.components.order.CheapComputerComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.CreditCardType;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_data.DataObjectBuilder;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;

public class BuyingCheapComputerTest extends BaseTest {

    @Test(dataProvider = "computerData")
    public void testCheapComputerBuying(ComputerData computerData) throws InterruptedException {
        WebDriver driver = getDriver();
        driver.get("https://demowebshop.tricentis.com/build-your-cheap-own-computer");
        int quantity = 2;
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, CheapComputerComponent.class, computerData, quantity);
        orderComputerFlow.buildComputerSpec();
        orderComputerFlow.addItemToCart();
        orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.agreeTOSAndCheckout();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
//        orderComputerFlow.selectPaymentMethod(PaymentMethod.CREDIT_CARD);
//        orderComputerFlow.inputPaymentInfo(CreditCardType.VISA);

        orderComputerFlow.selectPaymentMethod(PaymentMethod.CHECK_MONEY_ORDER);
        orderComputerFlow.verifyCODandCheckMoneyOrderPaymentInfo();
        orderComputerFlow.confirmOrder();


    }

    @DataProvider()
    public ComputerData[] computerData() {
        String relativeDataFileLocation = "/src/main/java/test_data/computer/CheapComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(relativeDataFileLocation, ComputerData[].class);
    }
}
