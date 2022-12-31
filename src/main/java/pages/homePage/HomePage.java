package pages.homePage;

import elementActions.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ElementActions {


	By registerLink = By.linkText("Register");
	By loginLink = By.linkText("Log in");
	By ContactUsLink = By.linkText("Contact us");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void openRegistrationPage()
	{
		clickButton(registerLink);
	}
	
	public void openLoginPage()
	{
		clickButton(loginLink);
	}

//	public void openContactUsPage()
//	{
//		ScrollToBottom();
//		ClickButton(ContactUsLink);
//	}


}
