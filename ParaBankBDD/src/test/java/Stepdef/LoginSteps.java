package Stepdef;

import Driver.DriverFactory;
import Utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;

public class LoginSteps {

    String url = ConfigReader.getProperty("url");
    String username = ConfigReader.getProperty("username");
    String password = ConfigReader.getProperty("password");

    private LoginPage loginPage;

    @Given("User is on the Parabank login page")
    public void user_is_on_the_parabank_login_page() {
        // Write code here  that turns the phrase above into concrete actions
        loginPage= new LoginPage(DriverFactory.getDriver());
        loginPage.openLoginPage(url);
    }
    @When("User enters valid username and password")
    public void user_enters_valid_username_and_password() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.enterCredentials(username, password);
    }
    @When("User clicks on the login button")
    public void user_clicks_on_the_login_button() {
        // Write code here that turns the phrase above into concrete actions
        loginPage.clickLoginButton();
    }
    @Then("User should be redirected to the account overview page")
    public void user_should_be_redirected_to_the_account_overview_page() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(
                loginPage.isAccountOverviewDisplayed(),
                "Accounts Overview page was not displayed"
        );
    }
    @Then("The page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        String actualTitle = loginPage.getPageTitle();

        Assert.assertEquals(
                actualTitle,
                expectedTitle,
                "Page title does not match"
        );
    }
}
