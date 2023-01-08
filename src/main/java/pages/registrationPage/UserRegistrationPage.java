package pages.registrationPage;

import elementActions.ElementActions;
import io.cucumber.java.an.E;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static elementActions.ElementActions.*;


public class UserRegistrationPage
{

	private WebDriver driver;

	By genderMaleRadioBtn = By.id("gender-male");
	By FirstName = By.id("FirstName");
	By LastName = By.id("LastName");;
	By Email = By.id("Email");;
	By Password = By.id("Password");;
	By ConfirmPassword = By.id("ConfirmPassword");;
	By registerBtn = By.id("register-button");;
	public By successMessage = By.cssSelector("div.result");
	public By logoutLink = By.linkText("Log out");
	By MyAccountLink = By.linkText("My account");

	public UserRegistrationPage(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Given User Navigated to Registration page")
	public UserRegistrationPage validateThatUserNavigatedToRegistrationPage(){
		Assert.assertTrue(driver.getCurrentUrl().contains("register"));
		return this;
	}

	@Step("When he fills registration form")
	public UserRegistrationPage fillUserRegistrationForm(String Firstname, String Lastname, String email, String password)
	{
		clickButton(genderMaleRadioBtn);
		Fill_in(FirstName, Firstname);
		Fill_in(LastName, Lastname);
		Fill_in(Email, email);
		Fill_in(Password, password);
		Fill_in(ConfirmPassword, password);
		return this;
	}

	@Step("And clicks on Register Button")
	public UserRegistrationPage clickOnRegisterButton(){
		clickButton(registerBtn);
		return this;
	}

	@Step("Then Success Message should be displayed")
	public UserRegistrationPage validateThatSuccessMessageShouldBeDisplayed(){
		Assert.assertTrue(ElementDisplayed(successMessage));
		return this;
	}

	@Step("User can logout")
	public void userlogout()
	{
		waitForVisibility(logoutLink);
		clickButton(logoutLink);
	}

	@Step("User can open My Account")
	public void openMyAccountPage()
	{
		clickButton(MyAccountLink);
	}
}
