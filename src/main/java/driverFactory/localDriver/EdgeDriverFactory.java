package driverFactory.localDriver;

import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverFactory extends DriverAbstract {

    @Override
    protected void startDriver() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }
}
