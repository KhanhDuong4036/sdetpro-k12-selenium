package test_flows.global;

import models.components.global.footer.*;
import models.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterTestFlow {

    private final WebDriver driver;

    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent() {
        BasePage basePage = new BasePage(this.driver);
        InformationColumnComponent informationColumnComponent = basePage.footerComp().informationColumnComponent();
        CustomerServiceColumnComponent customerServiceColumnComponent = basePage.footerComp().customerServiceColumnComponent();
        MyAccountColumnComponent myAccountColumnComponent = basePage.footerComp().myAccountColumnComponent();
        FollowUsColumnComponent followUsColumnComponent = basePage.footerComp().followUsColumnComponent();

        verifyInformationColumn(informationColumnComponent);
        verifyCustomerServiceColumn(customerServiceColumnComponent);
        verifyMyAccountColumn(myAccountColumnComponent);
//        verifyFollowUsColumn(followUsColumnComponent);
    }

    private void verifyInformationColumn(InformationColumnComponent informationColumnComponent) {
        List<String> expectedLinkTexts =
                Arrays.asList("Sitemap", "Shipping & Returns", "Privacy Notice", "Conditions of Use", "About us", "Contact us");
        List<String> expectedHrefs =
                Arrays.asList("/sitemap", "/shipping-returns", "/privacy-policy", "/conditions-of-use", "/about-us", "/contactus");
        testFooterColumn(informationColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyCustomerServiceColumn(CustomerServiceColumnComponent customerServiceColumnComponent) {
        List<String> expectedLinkTexts =
                Arrays.asList("Search", "News", "Blog", "Recently viewed products", "Compare products list", "New products");
        List<String> expectedHrefs =
                Arrays.asList("/search", "/news", "/blog", "/recentlyviewedproducts", "/compareproducts", "/newproducts");
        testFooterColumn(customerServiceColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void verifyFollowUsColumn(FollowUsColumnComponent followUsColumnComponent) {
        List<String> expectedLinkTexts =
                Arrays.asList("Facebook", "Twitter", "RSS", "YouTube", "Google+");
        List<String> expectedHrefs =
                Arrays.asList("http://www.facebook.com/nopCommerce", "https://twitter.com/nopCommerce", "/news/rss/1", "http://www.youtube.com/user/nopCommerce", "https://plus.google.com/+nopcommerce");
        testFooterColumn(followUsColumnComponent, expectedLinkTexts, expectedHrefs);

    }

    private void verifyMyAccountColumn(MyAccountColumnComponent myAccountColumnComponent) {
        List<String> expectedLinkTexts =
                Arrays.asList("My account", "Orders", "Addresses", "Shopping cart", "Wishlist");
        List<String> expectedHrefs =
                Arrays.asList("/customer/info", "/customer/orders", "/customer/addresses", "/cart", "/wishlist");
        testFooterColumn(myAccountColumnComponent, expectedLinkTexts, expectedHrefs);
    }

    private void testFooterColumn(FooterColumnComponent footerColumnComponent, List<String> expectedLinkTexts, List<String> expectedHrefs) {
        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefts = new ArrayList<>();
        expectedHrefs.replaceAll(originHref -> "https://demowebshop.tricentis.com" + originHref);

        footerColumnComponent.linksEle().forEach(columnItem -> {
            actualLinkTexts.add(columnItem.getText());
            actualHrefts.add(columnItem.getAttribute("href"));
        });

        if (actualLinkTexts.isEmpty() || actualHrefts.isEmpty()) {
            Assert.fail("Footer column texts OR hyperlinks is empty");
        }
        Assert.assertEquals(actualLinkTexts, expectedLinkTexts, "[ERR] Footer column link texts are different!!!");
        Assert.assertEquals(actualHrefts, expectedHrefs, "[ERR] Footer column hrefs are different!!!");

    }
}
