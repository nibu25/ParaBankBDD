package Stepdef;

import Utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.LoggerFactory;
import pages.UiTestingPlaypage;

import org.slf4j.Logger;

public class UiTestingPlaySteps {
    private static final Logger log =
            LoggerFactory.getLogger(UiTestingPlaySteps.class);
    String uiurl = ConfigReader.getProperty("testingplayurl");
    UiTestingPlaypage up;

    @Given("I am on the uitestingplayground page")
    public void i_am_on_the_uitestingplayground_page() {
        up = new UiTestingPlaypage(Driver.DriverFactory.getDriver());
        up.openPage(uiurl);
    }

    @When("I click on the Load Delay button")
    public void i_click_on_the_Load_Delay_button() {
        // Write code here that turns the phrase above into concrete actions
        up.clickLoadDelayButton();
    }

    @Then("I should see the text button")
    public void i_should_see_the_text_button() {
        // Write code here that turns the phrase above into concrete actions
        if (up.isButtonAppearingAfterDelayPresent() == true) {
            System.out.println("Button Appearing After Delay is present");
            up.clickButtonAppearingAfterDelay();

        }

    }
    @When("I click on the Progress Bar button")
    public void i_click_on_the_progress_bar_button() {
        // Write code here that turns the phrase above into concrete actions
        up.clickProgressBarButton();
    }
    @Then("I should see the progress bar complete")
    public void i_should_see_the_progress_bar_complete() {
        // Write code here that turns the phrase above into concrete actions
        up.startandstopProgressBar();
    }

    @When("I click on the Alerts button")
    public void i_click_on_the_alerts_button() {
        // Write code here that turns the phrase above into concrete actions
        up.clickAlertslink();
    }
    @Then("I click alert and closed the alert")
    public void i_click_alert_and_closed_the_alert() {
        // Write code here that turns the phrase above into concrete actions
        up.clickandacceptAlertButton();
        log.info("STEP 1: Alert step completed");
    }
    @Then("I click confirm and closed the confirm")
    public void i_click_confirm_and_closed_the_confirm() {
        // Write code here that turns the phrase above into concrete actions
        up.clickandacceptConfirmButton();
        log.info("STEP 2: Confirm step completed");
    }
    @Then("I click prompt and closed the prompt")
    public void i_click_prompt_and_closed_the_prompt() {
        // Write code here that turns the phrase above into concrete actions
        up.clickandacceptPromptButton();
        log.info("STEP 3: Prompt step completed");
    }
}