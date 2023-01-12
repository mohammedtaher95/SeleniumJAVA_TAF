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
import org.testng.Reporter;
import tools.properties.DefaultProperties;

import java.io.IOException;


public class Webdriver {


    private static ThreadLocal<org.openqa.selenium.WebDriver> Driver = new ThreadLocal<>();


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
    }


    protected static void setDriver(WebDriver driver){
        Driver.set(driver);
    }

    public static WebDriver getDriver(){
        return Driver.get();
    }

}
