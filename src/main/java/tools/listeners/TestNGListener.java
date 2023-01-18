package tools.listeners;

import constants.CrossBrowserMode;
import driverFactory.Webdriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.testng.*;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlMethodSelector;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import tools.listeners.helpers.TestNGSuiteHelper;
import tools.properties.DefaultProperties;
import utilities.AllureBatchGenerator;
import utilities.Classesloader;
import utilities.ScreenshotHelper;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import static tools.properties.PropertiesHandler.*;

public class TestNGListener implements IAlterSuiteListener, ITestListener, ISuiteListener,
        IExecutionListener, IInvokedMethodListener {

    static XmlSuite testSuite;
    static XmlTest test;

    @Override
    public void onExecutionStart() {

    }

    @Override
    public void onExecutionFinish() {
        if(DefaultProperties.reporting.automaticOpenAllureReport() == true){
            try {
                Runtime.getRuntime().exec("generateAllureReport.bat");
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    @Override
    public void onStart(ISuite suite) {
        try {
            AllureBatchGenerator.generateBatFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alter(List<XmlSuite> suites){
        try {
            initializeProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

        testSuite = suites.get(0);

        try {
            TestNGSuiteHelper.SuiteGenerator(testSuite);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
