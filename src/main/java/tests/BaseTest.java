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
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {

    private static final List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String browser;

    protected WebDriver getDriver(){
        return driverThread.get().getDriver(this.browser);
    }

    @BeforeTest(description = "Init browser session")
    @Parameters({"browser"})
    protected void initBrowserSession(String browser) {
        this.browser = browser;
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory threadDriverFactory = new DriverFactory();
            webDriverThreadPool.add(threadDriverFactory);
            return threadDriverFactory;
        });
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowserSession() {
        driverThread.get().closeBrowserSession();
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
        WebDriver driver = driverThread.get().getDriver(this.browser);
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
