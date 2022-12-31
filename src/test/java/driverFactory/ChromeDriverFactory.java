package driverFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverFactory extends DriverAbstract {

    @Override
    protected void startDriver() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
}
