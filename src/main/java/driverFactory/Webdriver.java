package driverFactory;

import constants.CrossBrowserMode;
import constants.DriverType;
import constants.EnvType;
import driverFactory.localDriver.ChromeDriverFactory;
import driverFactory.localDriver.DriverAbstract;
import driverFactory.localDriver.EdgeDriverFactory;
import driverFactory.localDriver.FirefoxDriverFactory;
import driverFactory.remoteDriver.GridConfig;
import org.openqa.selenium.WebDriver;
import org.testng.TestNG;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import tools.properties.DefaultProperties;

import java.io.IOException;
import java.util.*;

import static tools.listeners.TestNGListener.*;
import static tools.properties.PropertiesHandler.readPropertyFile;

public class Webdriver {


    private static ThreadLocal<org.openqa.selenium.WebDriver> Driver = new ThreadLocal<>();


    public Webdriver() throws IOException {

        if(EnvType.valueOf(DefaultProperties.platform.EnvironmentType()) == EnvType.LOCAL){
            localDriverInit(DefaultProperties.capabilities.targetBrowserName());
        }

        if(EnvType.valueOf(DefaultProperties.platform.EnvironmentType()) == EnvType.GRID){
            GridConfig.gridInit();
        }

        if(EnvType.valueOf(DefaultProperties.platform.EnvironmentType()) == EnvType.CLOUD){
            //remoteDriverInit(browserName);
        }

        xmlGenerator();

        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());
    }


    public void xmlGenerator() throws IOException {
        XmlSuite suite = new XmlSuite();
        suite.setName("WebDriver Suite");
        suite.setThreadCount(2);
        if(mode == CrossBrowserMode.PARALLEL){
            suite.setParallel(XmlSuite.ParallelMode.TESTS);
        }

        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("browserName", "chrome");

        XmlTest chromeTest = new XmlTest(suite);
        chromeTest.setName("Chrome Test");
        chromeTest.setParameters(hashMap);
        chromeTest.setThreadCount(0);
        List<XmlClass> classes = new ArrayList<XmlClass>();
        classes.add(new XmlClass("tests.TestClass"));
        chromeTest.setXmlClasses(classes);

        XmlTest firefoxTest = new XmlTest(suite);
        firefoxTest.setName("Firefox Test");
        firefoxTest.setThreadCount(0);

        firefoxTest.setParameters(hashMap);
        firefoxTest.setXmlClasses(classes);

        List<XmlSuite> suites = new ArrayList<XmlSuite>();

        suites.add(suite);
        TestNG testng = new TestNG();
        testng.setXmlSuites(suites);

//        Path destination = Paths.get(".", "TestNG.xml");
//        Files.createDirectories(destination.getParent());
//        FileOutputStream outputStream = new FileOutputStream(destination.toString());
//        outputStream.write(suite.toXml());
//        outputStream.close();

        //System.out.println(suite.toXml());
        //testng.run();


    }

    public void localDriverInit(String browserName){
        setDriver(getDriverFactory(DriverType.valueOf(browserName.toUpperCase()))
                .getDriver());
        getDriver().navigate().to(DefaultProperties.capabilities.baseURL());
    }



    public static DriverAbstract getDriverFactory(DriverType driverType){
        switch (driverType){
            case CHROME:
            {
                return new ChromeDriverFactory();
            }
            case FIREFOX:
            {
                return new FirefoxDriverFactory();
            }
            case EDGE:
            {
                return new EdgeDriverFactory();
            }
            default:
                throw new IllegalStateException("Unexpected value: " + driverType);
        }
    }

    public void quit() throws IOException {
        getDriver().manage().deleteAllCookies();
        getDriver().quit();
        Driver.remove();
        //Runtime.getRuntime().exec("generateAllureReport.bat");
    }


    protected static void setDriver(WebDriver driver){
        Driver.set(driver);
    }

    public static WebDriver getDriver(){
        return Driver.get();
    }

}
