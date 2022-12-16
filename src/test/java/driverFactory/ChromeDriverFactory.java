package driverFactory;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverFactory extends DriverAbstract {

    @Override
    protected void startDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
}
