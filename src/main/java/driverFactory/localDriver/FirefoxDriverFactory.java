package driverFactory.localDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import tools.properties.DefaultProperties;

public class FirefoxDriverFactory extends DriverAbstract {

    @Override
    protected void startDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--" + DefaultProperties.capabilities.executionMethod(), "--window-size=1920,1080");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
    }
}
