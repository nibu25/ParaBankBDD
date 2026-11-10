package Stepdef;

import Driver.DriverFactory;
import Utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RedbusPage;

public class BusBookingSteps {
    String url = ConfigReader.getProperty("redbus");
    RedbusPage rp;
    @Given("I am on the redbus Bangalore bus search page")
    public void i_am_on_the_redbus_bangalore_bus_search_page() {
        rp= new RedbusPage(DriverFactory.getDriver());
        rp.openRedbusPage(url);

    }
    @When("I enter {string} in the source field")
    public void i_enter_in_the_source_field(String string) throws InterruptedException {
        rp.enterSource(string);
        rp.selectSourceFromList(string);
    }
    @When("I enter {string} in the destination field")
    public void i_enter_in_the_destination_field(String string) throws InterruptedException {
        rp.enterDestination(string);
        rp.selectDestinationFromList(string);
//        rp.checkDestinationselected(string);
    }
    @When("I enter the date in the date field")
    public void i_enter_the_date_in_the_date_field() {
        rp.selectJourneyDate("November", "5");
    }
    @When("I select booking for women only")
    public void i_select_booking_for_women_only() {
        rp. selectWomenOnlyBooking();
    }
    @When("I click on the search button")
    public void i_click_on_the_search_button() throws InterruptedException {
    rp.clickSearchButton();
    }
    @Then("I should see the list of buses")
    public void i_should_see_the_list_of_buses() {
    rp.listOfBusFound();    }
}
