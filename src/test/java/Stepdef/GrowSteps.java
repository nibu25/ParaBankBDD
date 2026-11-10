package Stepdef;

import Utils.ConfigReader;
import io.cucumber.java.en.*;
import org.testng.asserts.SoftAssert;
import pages.GrawCalcpage;

public class GrowSteps {
    private final String url = ConfigReader.getProperty("grow");
    private GrawCalcpage gp;

    @Given("I am on the Groww EMI calculator page")
    public void openPage() {
        gp = new GrawCalcpage(Driver.DriverFactory.getDriver());
        gp.openGrowwPage(url);
    }

    @When("I enter {string} in the loan amount field")
    public void enterLoan(String v) { gp.enterLoanAmount(Long.parseLong(v)); }

    @When("I enter {string} in the rate of interest field")
    public void enterRate(String v) { gp.enterRate(Double.parseDouble(v)); }

    @When("I enter {string} in the loan tenure field")
    public void enterTenure(String v) { gp.enterTenure(Double.parseDouble(v)); }

    @Then("I should see the EMI details as monthly EMI {string}, principal amount {string}, total interest {string} and total amount {string}")
    public void verify(String emi, String principal, String interest, String total) {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(num(gp.getMonthlyEmi()), Long.parseLong(emi), "Monthly EMI");
        soft.assertEquals(num(gp.getPrincipalAmount()), Long.parseLong(principal), "Principal amount");
        soft.assertEquals(num(gp.getTotalInterest()), Long.parseLong(interest), "Total interest");
        soft.assertEquals(num(gp.getTotalAmount()), Long.parseLong(total), "Total amount");
        soft.assertAll();
    }

    private long num(String s) { return Long.parseLong(s.replaceAll("[^0-9]", "")); }
}