package coreConfig;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import utilities.ScreenshotHelper;

import java.io.IOException;

public class Hooks{

    private final Config config = new Config();

    public Hooks() throws IOException {

    }

    @Before(order = 1)
    public void startingDriver() throws IOException {

    }

    @After(order = 1)
    @Attachment(value = "Page screenshot", type = "image/jpg")
    public void screenshotOnFailure(Scenario scenario) throws IOException {
        if(scenario.isFailed()){
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
            String fullPath = System.getProperty("user.dir")
                    + String.valueOf(ScreenshotHelper.captureScreenshot(config.getDriver(), scenario.getName()));
            Allure.addAttachment(scenario.getName(), fullPath);
        }
    }

    @After(order = 2)
    public void quittingDriver(){
        config.quitDriver();
    }
}
