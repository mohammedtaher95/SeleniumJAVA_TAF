package tests;

import driverFactory.Webdriver;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.homePage.HomePage;
import pages.registrationPage.UserRegistrationPage;
import utilities.UserFormData;

import java.io.IOException;

import static browserActions.BrowserActions.navigateToURL;
import static elementActions.ElementActions.clickButton;

public class TestClass {

    driverFactory.Webdriver driver;

    HomePage home;
    UserFormData newUser;
    By registerLink = By.linkText("Register");

    @BeforeMethod(description = "Setup Driver")
    public void setUp() throws IOException {
        System.setProperty("BASE_URL", "http://demo.nopcommerce.com");
        driver = new driverFactory.Webdriver();
        navigateToURL("http://demo.nopcommerce.com");
    }

    @Description("User can access registration page and register successfully")
    @Test(description = "User Register on website successfully")
    public void testMethod(){

        //clickButton(registerLink);

        home = new HomePage(Webdriver.getDriver());
        newUser = new UserFormData();
        home.openRegistrationPage();

        new UserRegistrationPage(Webdriver.getDriver())
                .validateThatUserNavigatedToRegistrationPage()
                .fillUserRegistrationForm(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getOldPassword())
                .clickOnRegisterButton()
                .validateThatSuccessMessageShouldBeDisplayed();

    }

    @AfterMethod(description = "Teardown")
    public void tearDown() throws IOException {
        driver.quit();
    }
}
