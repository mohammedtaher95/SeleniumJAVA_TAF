package tools.listeners;

//import driverFactory.Webdriver;

import constants.CrossBrowserMode;
import constants.EnvType;
import driverFactory.Webdriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.testng.*;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import tools.properties.DefaultProperties;
import utilities.ScreenshotHelper;
//import tools.utilities.ScreenshotHelper;


import java.io.IOException;
import java.util.Properties;

import static tools.properties.PropertiesHandler.*;

public class TestNGListener implements ITestListener, ISuiteListener,
        IExecutionListener, IInvokedMethodListener {

    public static Properties executionPlatform;
    protected static Properties WebCapabilities;
    protected static Properties MobileCapabilities;
    public static String URL;
    public static EnvType envType;
    public static CrossBrowserMode mode;
    public static String browserName;

    XmlSuite suite;
    XmlTest test;

    @Override
    public void onExecutionStart() {

        try {
            initializeProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onExecutionFinish() {
        try {
            Runtime.getRuntime().exec("generateAllureReport.bat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // TODO document why this method is empty
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("Success of test cases and its details are : " + result.getName());
    }

    @Override
    @Attachment(value = "Page screenshot", type = "image/jpg")
    public void onTestFailure(ITestResult result) {
        System.out.println("Failure of test cases and its details are : " + result.getName());
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
            String fullPath = null;
            try {
                fullPath = System.getProperty("user.dir")
                        + ScreenshotHelper.captureScreenshot(Webdriver.getDriver(), result.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fullPath != null;
            Allure.addAttachment(result.getName(), fullPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Skip of test cases and its details are : " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Failure of test cases and its details are : " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {


    }

    @Override
    public void onFinish(ITestContext context) {

    }

}
