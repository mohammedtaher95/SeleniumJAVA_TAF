package coreConfig;

import constants.DriverType;
import constants.EnvType;
import driverFactory.DriverFactoryAbstract;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import static utilities.PropertiesHandler.readPropertyFile;

public class Config {

    protected Properties properties;
    protected String URL;
    protected static EnvType envType;
    protected String localBrowserName;
    protected String remoteBrowserName;

    private static ThreadLocal<WebDriver> Driver;

    public Config() throws IOException {
        properties = readPropertyFile("properties/environment.properties");
        envType = EnvType.valueOf(properties.getProperty("ENV_TYPE"));
        URL = properties.getProperty("TEST_BASE_URL");
        localBrowserName = properties.getProperty("LOCAL_BROWSER_NAME");
        remoteBrowserName = properties.getProperty("GRID_BROWSER_NAME");
    }

    public void setUp(String browserName) throws IOException {

        if(envType == EnvType.LOCAL){
            localDriverInit();
        }

        if(envType == EnvType.GRID){
            gridInit(browserName);
        }

        if(envType == EnvType.CLOUD){
            //remoteDriverInit(browserName);
        }
    }

    public void localDriverInit(){
        setDriver(DriverFactoryAbstract.getDriverFactory(DriverType.valueOf(localBrowserName.toUpperCase())).getDriver());
        getDriver().navigate().to(URL);
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "DRIVER = " + getDriver());
    }

    public void gridInit(String browserName) throws MalformedURLException {
        Driver = new ThreadLocal<>();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        setDriver(new RemoteWebDriver(new URL(properties.getProperty("REMOTE_ENV_URL")), capabilities));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        getDriver().manage().window().maximize();
        getDriver().navigate().to(URL);
    }

    public void quitDriver(){
        getDriver().manage().deleteAllCookies();
        getDriver().quit();
        Driver.remove();
    }

    /////////////////////////////Getters & Setters///////////////////////////////////////////////////

    private static void setDriver(WebDriver driver){
        Driver.set(driver);
    }

    public static WebDriver getDriver(){
        return Driver.get();
    }

}
