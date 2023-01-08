package driverFactory.remoteDriver;

import driverFactory.Webdriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import tools.properties.DefaultProperties;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static tools.listeners.TestNGListener.executionPlatform;
import static tools.listeners.TestNGListener.*;

public class GridConfig extends Webdriver {

    //ITest Context from TestNG to read XML File
    //static ITestContext context;
    //Reporter.getCurrentTestResult().
    //                getTestContext().getCurrentXmlTest().getParameter("browserName")

    public GridConfig() throws IOException {
    }

    //@Parameters(value = {"browserName"})
    public static void gridInit() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", Reporter.getCurrentTestResult().
                            getTestContext().getCurrentXmlTest().getParameter("browserName"));
        setDriver(new RemoteWebDriver(new URL(DefaultProperties.platform.RemoteURL()), capabilities));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().manage().window().maximize();
        getDriver().navigate().to(DefaultProperties.capabilities.baseURL());
    }
}
