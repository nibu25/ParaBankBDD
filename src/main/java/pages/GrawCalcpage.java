package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GrawCalcpage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public GrawCalcpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // built after driver is set
    }

    public void openGrowwPage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        // implicit wait removed: mixing it with explicit waits causes unpredictable delays
    }

    // one slider locator for all three sliders
    private By sliderFor(String label) {
        return By.xpath("//div[contains(text(),'" + label + "')]/following::div[@role='slider'][1]");
    }

    public void enterLoanAmount(long v)   { setSlider("Loan amount", v); }
    public void enterRate(double v)       { setSlider("Rate of interest", v); }
    public void enterTenure(double v)     { setSlider("Loan tenure", v); }

    private void setSlider(String label, double target) {
        By locator = sliderFor(label);
        WebElement thumb = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        double min = Double.parseDouble(thumb.getAttribute("aria-valuemin"));
        double max = Double.parseDouble(thumb.getAttribute("aria-valuemax"));
        if (target < min || target > max) {
            throw new IllegalArgumentException(label + ": value " + target
                    + " outside slider range " + min + "-" + max);
        }

        // 1. Rough drag
        double current = Double.parseDouble(thumb.getAttribute("aria-valuenow"));
        int travel = thumb.findElement(By.xpath("./..")).getSize().getWidth() - thumb.getSize().getWidth();
        int dx = (int) Math.round((target - current) * travel / (max - min));
        new Actions(driver).clickAndHold(thumb).moveByOffset(dx, 0).release().perform();

        // 2. Fine-tune with arrow keys
        for (int i = 0; i < 200; i++) {
            thumb = driver.findElement(locator); // re-find to avoid stale element
            double now = Double.parseDouble(thumb.getAttribute("aria-valuenow"));
            if (Math.abs(now - target) < 0.0001) return;
            thumb.sendKeys(now < target ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT);
        }
        throw new AssertionError(label + ": could not reach " + target);
    }

    // VERIFY these two locators in DevTools: they are guesses from the visible labels
    private By valueFor(String label) {
        return By.xpath("//*[normalize-space()='" + label + "']/following-sibling::*[1]");
    }
    public String getMonthlyEmi()      { return text("Monthly EMI"); }
    public String getPrincipalAmount() { return text("Principal amount"); }
    public String getTotalInterest()   { return text("Total interest"); }
    public String getTotalAmount()     { return text("Total amount"); }

    private String text(String label) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(valueFor(label))).getText();
    }
}