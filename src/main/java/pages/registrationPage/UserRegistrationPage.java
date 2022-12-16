package pages.registrationPage;

import elementActions.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class UserRegistrationPage extends ElementActions
{

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
		super(driver);
	}

	public void userRegistration(String Firstname, String Lastname, String email, String password)
	{
		clickButton(genderMaleRadioBtn);
		Fill_in(FirstName, Firstname);
		Fill_in(LastName, Lastname);
		Fill_in(Email, email);
		Fill_in(Password, password);
		Fill_in(ConfirmPassword, password);
		clickButton(registerBtn);
	}
	
	public void userlogout()
	{
		clickButton(logoutLink);
	}
	
	public void openMyAccountPage()
	{
		clickButton(MyAccountLink);
	}
}
