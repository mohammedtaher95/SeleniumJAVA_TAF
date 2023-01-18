package driverFactory.remoteDriver;

import driverFactory.Webdriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import tools.properties.DefaultProperties;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class GridConfig extends Webdriver {

    //ITest Context from TestNG to read XML File
    //static ITestContext context;
    //Reporter.getCurrentTestResult().
    //                getTestContext().getCurrentXmlTest().getParameter("browserName")

    static ITestContext context;

    public GridConfig() throws IOException {
    }


    public static void gridInit() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String browserName = Reporter.getCurrentTestResult().
                getTestContext().getCurrentXmlTest().getParameter("browserName");
        capabilities.setCapability("browserName", browserName);
        setDriver(new RemoteWebDriver(new URL(DefaultProperties.platform.RemoteURL()), capabilities));

    }
}
