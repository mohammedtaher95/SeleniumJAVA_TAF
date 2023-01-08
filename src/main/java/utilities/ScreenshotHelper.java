package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotHelper {

    public static Path captureScreenshot(WebDriver driver, String screenshotName) throws IOException {

        Path destination = Paths.get("./screenshots", screenshotName + ".jpg");
        Files.createDirectories(destination.getParent());
        FileOutputStream outputStream = new FileOutputStream(destination.toString());
        //outputStream.write();
        //new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        outputStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        outputStream.close();

        return destination;
    }

    //    public void screenshotOnFailure(ITestResult testResult) throws IOException {
//        if(testResult.getStatus() == ITestResult.FAILURE){
//            System.out.println("Failed!");
//            System.out.println("Taking Screenshot....");
//            String fullPath = System.getProperty("user.dir")
//                    + String.valueOf(ScreenshotHelper.captureScreenshot(getDriver(), testResult.getName()));
//            Allure.addAttachment(testResult.getName(), fullPath);
//        }
//    }
}
