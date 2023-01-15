package driverFactory;

import browserActions.BrowserActions;
import constants.DriverType;
import constants.EnvType;
import driverFactory.localDriver.*;
import driverFactory.remoteDriver.GridConfig;
import elementActions.ElementActions;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import tools.properties.DefaultProperties;

import java.io.IOException;
import java.time.Duration;


public class Webdriver{


    private static final ThreadLocal<org.openqa.selenium.WebDriver> Driver = new ThreadLocal<>();


    public Webdriver() throws IOException {

        if(EnvType.valueOf(DefaultProperties.platform.EnvironmentType()) == EnvType.LOCAL){
            localDriverInit(Reporter.getCurrentTestResult().
                    getTestContext().getCurrentXmlTest().getParameter("browserName"));
        }

        if(EnvType.valueOf(DefaultProperties.platform.EnvironmentType()) == EnvType.GRID){
            GridConfig.gridInit();
        }

        if(EnvType.valueOf(DefaultProperties.platform.EnvironmentType()) == EnvType.CLOUD){
            //remoteDriverInit(browserName);
        }

        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());

        BrowserActions actions = new BrowserActions();
        ElementActions actions1 = new ElementActions();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

        if(!DefaultProperties.capabilities.baseURL().isEmpty())
        {
            getDriver().navigate().to(DefaultProperties.capabilities.baseURL());
        }
    }


    public void localDriverInit(String browserName){
        setDriver(DriverFactory.getDriverFactory(DriverType.valueOf(browserName.toUpperCase()))
                .getDriver());
    }


    public void quit() throws IOException {
        getDriver().manage().deleteAllCookies();
        getDriver().quit();
        Driver.remove();
    }


    protected static void setDriver(WebDriver driver){
        Driver.set(driver);
    }

    public WebDriver makeAction(){
        return Driver.get();
    }

    public static WebDriver getDriver(){
        return Driver.get();
    }

}
