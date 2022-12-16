package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

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
        outputStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        outputStream.close();

        return destination;
    }
}
