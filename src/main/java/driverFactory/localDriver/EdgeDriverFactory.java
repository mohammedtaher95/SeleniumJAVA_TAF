package driverFactory.localDriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import tools.properties.DefaultProperties;

public class EdgeDriverFactory extends DriverAbstract {

    @Override
    protected void startDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--" + DefaultProperties.capabilities.executionMethod(), "--window-size=1920,1080");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
    }
}
