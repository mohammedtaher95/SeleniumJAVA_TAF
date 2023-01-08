package elementActions;


import driverFactory.Webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementActions {

    private static WebDriver driver;
    public JavascriptExecutor JSE;
    public Select dropDown;
    public static WebDriverWait wait;

    public ElementActions(){
        this.driver = Webdriver.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public static void clickButton(By Btn){
        driver.findElement(Btn).click();
    }

    public static void Fill_in(By field, String value){
        WebElement Field = driver.findElement(field);
        Field.clear();
        Field.sendKeys(value);
    }

    public static boolean ElementDisplayed(By by){
        return driver.findElement(by).isDisplayed();
    }

    public void SelectItemByIndex(By by, int index)
    {
        new Select(driver.findElement(by)).selectByIndex(index);
    }

    public void SelectItemByText(By by, String text)
    {
        new Select(driver.findElement(by)).selectByVisibleText(text);
    }

    public String getElementText(By by){
        return driver.findElement(by).getText();
    }

    public static void waitForVisibility(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
