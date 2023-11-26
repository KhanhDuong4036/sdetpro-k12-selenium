package test_flows.global;

import models.components.global.footer.*;
import models.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterTestFlow {

    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent(){
        BasePage basePage = new BasePage(this.driver);
        InformationColumnComponent informationColumnComponent= basePage.footerComp().informationColumnComponent();
        CustomerServiceColumnComponent customerServiceColumnComponent = basePage.footerComp().customerServiceColumnComponent();
        MyAccountColumnComponent myAccountColumnComponent = basePage.footerComp().myAccountColumnComponent();
        FollowUsColumnComponent followUsColumnComponent = basePage.footerComp().followUsColumnComponent();

        verifyInformationColumn(informationColumnComponent);
        verifyCustomerServiceColumn(customerServiceColumnComponent);
        verifyMyAccountColumn(myAccountColumnComponent);
        verifyFollowUsColumn(followUsColumnComponent);
    }

    private void verifyInformationColumn(InformationColumnComponent informationColumnComponent) {
        testFooterColumn(informationColumnComponent);
    }

    private void verifyCustomerServiceColumn(CustomerServiceColumnComponent customerServiceColumnComponent) {
        testFooterColumn(customerServiceColumnComponent);
    }

    private void verifyFollowUsColumn(FollowUsColumnComponent followUsColumnComponent) {
        testFooterColumn(followUsColumnComponent);

    }

    private void verifyMyAccountColumn(MyAccountColumnComponent myAccountColumnComponent) {
        testFooterColumn(myAccountColumnComponent);
    }

    private void testFooterColumn(FooterColumnComponent footerColumnComponent){
        System.out.println(footerColumnComponent.headerEle().getText());
        for (WebElement linkEle : footerColumnComponent.linksEle()) {
            System.out.println(linkEle.getText()+ ": " + linkEle.getAttribute("href"));
        }
        System.out.println("=====");
    }
}
