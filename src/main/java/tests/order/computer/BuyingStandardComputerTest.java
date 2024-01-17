package tests.order.computer;

import models.components.order.StandardComputerComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.CreditCardType;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_data.DataObjectBuilder;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;

public class BuyingStandardComputerTest extends BaseTest {

    @Test(dataProvider = "computerData")
    public void testStandardComputerBuying(ComputerData computerData){
        WebDriver driver = getDriver();
        driver.get("https://demowebshop.tricentis.com/build-your-own-computer");
        int quantity = 2;
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, StandardComputerComponent.class, computerData, quantity);
        orderComputerFlow.buildComputerSpec();
        orderComputerFlow.addItemToCart();
        orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.agreeTOSAndCheckout();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod(PaymentMethod.CREDIT_CARD);
        orderComputerFlow.inputPaymentInfo(CreditCardType.VISA);
        orderComputerFlow.confirmOrder();
    }
    @DataProvider()
    public ComputerData[] computerData() {
        String relativeDataFileLocation = "/src/main/java/test_data/computer/StandardComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(relativeDataFileLocation, ComputerData[].class);
    }
}
