package tools.listeners;

import constants.CrossBrowserMode;
import driverFactory.Webdriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.testng.*;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import tools.properties.DefaultProperties;
import utilities.ScreenshotHelper;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import static tools.properties.PropertiesHandler.*;

public class TestNGListener implements IAlterSuiteListener, ITestListener, ISuiteListener,
        IExecutionListener, IInvokedMethodListener {

    static XmlSuite suite;
    static XmlTest test;

    @Override
    public void onExecutionStart() {

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

    @Override
    public void onStart(ISuite suite) {
    }

    @Override
    public void alter(List<XmlSuite> suites){
        try {
            initializeProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

        suite = suites.get(0);
        test = suite.getTests().get(0);

        suite.setName("WebDriver Suite");
        if(CrossBrowserMode.valueOf(DefaultProperties.platform.CrossBrowserMode()) == CrossBrowserMode.PARALLEL){
            initializeParallelExecution();
        }

        if(CrossBrowserMode.valueOf(DefaultProperties.platform.CrossBrowserMode()) == CrossBrowserMode.SEQUENTIAL){
            initializeSequentialExecution();
        }

        if(CrossBrowserMode.valueOf(DefaultProperties.platform.CrossBrowserMode()) == CrossBrowserMode.OFF){
            initializeNormalExecution();
        }

        Path destination = Paths.get(".", "TestNG.xml");
        File newFile = new File("TestNG.xml");

        try {
            Files.delete(destination);
            Files.writeString(destination, suite.toXml());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeParallelExecution(){
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(2);

        XmlTest chromeTest = test;
        chromeTest.setName("Chrome Test");
        chromeTest.addParameter("browserName", "chrome");
        chromeTest.setThreadCount(1);
        chromeTest.setParallel(XmlSuite.ParallelMode.NONE);
        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass("tests.TestClass"));
        chromeTest.setXmlClasses(classes);

        XmlTest firefoxTest = new XmlTest(suite);
        firefoxTest.setName("Firefox Test");
        firefoxTest.setThreadCount(1);
        firefoxTest.setParallel(XmlSuite.ParallelMode.NONE);
        firefoxTest.addParameter("browserName", "firefox");
        firefoxTest.setXmlClasses(classes);
    }

    private static void initializeSequentialExecution(){
        suite.setParallel(XmlSuite.ParallelMode.NONE);
        suite.setThreadCount(2);

        XmlTest chromeTest = test;
        chromeTest.setName("Chrome Test");
        chromeTest.addParameter("browserName", "chrome");
        chromeTest.setThreadCount(1);
        chromeTest.setParallel(XmlSuite.ParallelMode.NONE);
        ClassLoader stream = ClassLoader.getSystemClassLoader();
        stream.getDefinedPackage("tests").getClass().getName();

        List<XmlClass> classes = new ArrayList<>();
        //classes.forEach(c->);

        classes.add(new XmlClass("tests.TestClass"));
        chromeTest.setXmlClasses(classes);

        XmlTest firefoxTest = new XmlTest(suite);
        firefoxTest.setName("Firefox Test");
        firefoxTest.setThreadCount(1);
        firefoxTest.setParallel(XmlSuite.ParallelMode.NONE);
        firefoxTest.addParameter("browserName", "firefox");
        firefoxTest.setXmlClasses(classes);
    }

    private static void initializeNormalExecution(){
        suite.setParallel(XmlSuite.ParallelMode.NONE);
        //XmlTest test = new XmlTest(suite);
        test.setName("Test");
        test.addParameter("browserName", DefaultProperties.capabilities.targetBrowserName());
        test.setThreadCount(1);
        test.setParallel(XmlSuite.ParallelMode.NONE);
        List<XmlClass> classes = new ArrayList<>();
        classes.add(new XmlClass("tests.TestClass"));
        test.setXmlClasses(classes);
    }

}
