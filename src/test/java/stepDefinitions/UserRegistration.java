package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.homePage.HomePage;
import pages.registrationPage.UserRegistrationPage;
import utilities.UserFormData;

import static coreConfig.Config.getDriver;

public class UserRegistration {

    HomePage home;
    UserRegistrationPage register;
    UserFormData newUser;

    @Given("the user in the homepage")
    public void the_user_in_the_homepage() {
        // Write code here that turns the phrase above into concrete actions
        home = new HomePage(getDriver());
        newUser = new UserFormData();
        home.openRegistrationPage();
    }
    @When("he clicks on the register link")
    public void he_clicks_on_the_register_link() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(getDriver().getCurrentUrl().contains("register"));
    }

    @When("enters his firstname, lastname, email, password")
    public void enters_his_firstname_lastname_email_password() {
        // Write code here that turns the phrase above into concrete actions
        register = new UserRegistrationPage(getDriver());
        register.userRegistration(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getOldPassword());
    }

    @Then("the registration page should be displayed successfully")
    public void the_registration_page_should_be_displayed_successfully() {
        // Write code here that turns the phrase above into concrete actions
        register.userlogout();
    }

}
