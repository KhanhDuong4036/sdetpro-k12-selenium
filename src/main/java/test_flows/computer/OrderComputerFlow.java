package test_flows.computer;

import models.components.cart.TotalComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import test_data.ComputerData;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private Class<T> computerEssentialCompClass;
    private WebDriver driver;
    private ComputerData computerData;
    private double  itemTotalPrice;
    private int  quantity;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialCompClass, ComputerData computerData){
        this.computerEssentialCompClass = computerEssentialCompClass;
        this.driver = driver;
        this.computerData = computerData;
        this.quantity = 1;
    }
    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialCompClass, ComputerData computerData, int quantity){
        this.computerEssentialCompClass = computerEssentialCompClass;
        this.driver = driver;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildComputerSpec(){
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
        if (osOption != null){
            String osFullStr = computerEssentialComponent.selectOS(osOption);
            osAdditionalPrice = extractAdditionalPrice(osFullStr);
        }

        // Set item quantity
        computerEssentialComponent.setProductQuantity(this.quantity);
        double additionalPrice = processorAdditionalPrice + ramAdditionalPrice + hddAdditionalPrice + softwareAdditionalPrice + osAdditionalPrice;
        this.itemTotalPrice = (computerEssentialComponent.productPrice() + additionalPrice) * this.quantity;

    }

    public void addItemToCart(){
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        ComputerEssentialComponent computerEssentialComponent = new ComputerItemDetailsPage(driver).computerComp(computerEssentialCompClass);
        computerEssentialComponent.clickOnAddToCartBtn();
        computerEssentialComponent.waitUntilItemAddedToCart();
        computerItemDetailsPage.headerComp().clickOnShoppingCartLink();

    }

    public void verifyShoppingCartPage(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        TotalComponent totalComponent = shoppingCartPage.totalComponent();
        Map<String, Double> priceCategories = totalComponent.priceCategories();
        for (String priceType : priceCategories.keySet()) {
            System.out.printf("%s: %f\n", priceType, priceCategories.get(priceType));
        }
    }



    private double extractAdditionalPrice(String optionStr){
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(optionStr);
        if (matcher.find()){
            price = Double.parseDouble(matcher.group(1).replaceAll("[+-]", ""));
        }
        return price;
    }


}
