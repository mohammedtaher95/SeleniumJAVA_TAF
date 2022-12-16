package driverFactory;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverFactory extends DriverAbstract {

    @Override
    protected void startDriver() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
}
