package tests;

import driver.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeTest
    protected void initBrowserSession() {
        driver = DriverFactory.getWebDriver();
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowserSession() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        boolean isTestFailed = result.getStatus() == ITestResult.FAILURE;
        if (isTestFailed) {
            attachScreenshot(result);
        }
    }

    private void attachScreenshot(ITestResult result) {
        String methodName = result.getName();
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String fileName = methodName + "-" + year + "-" + month + "-" + date + "-" + hour + "-" + minute + "-" + second + ".png";
        File screenshotBase64Data = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String fileLocation = System.getProperty("user.dir") + "/screenshots/" + fileName;
            FileUtils.copyFile(screenshotBase64Data, new File(fileLocation));
            Path content = Paths.get(fileLocation);
            try (InputStream inputStream = Files.newInputStream(content)) {
                Allure.addAttachment(methodName, inputStream);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
