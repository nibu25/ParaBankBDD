package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RedbusPage {
    private WebDriver driver;
    // Constructor
    public RedbusPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to open the Redbus page
    public void openRedbusPage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    By sourceField = By.id("srcinput");
    //method for entering source
    public void enterSource(String source) {
        driver.findElement(sourceField).sendKeys(source);
    }

    By sourcelist= By.xpath("//div[starts-with(@id,'suggestion-') and @role='button']");
    public void selectSourceFromList(String source) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(sourcelist));
        List<WebElement> sources=driver.findElements(sourcelist);
        for(WebElement s:sources){
            if(s.getText().toLowerCase().contains(source.toLowerCase())){
                wait.until(ExpectedConditions.elementToBeClickable(s)).click();
                break;
            }
        }
    }

    By destinationField = By.id("destinput");
    //method for entering destination
    public void enterDestination(String destination) {
//        driver.findElement(destinationField).sendKeys(Keys.TAB);
        driver.findElement(destinationField).click();
        driver.findElement(destinationField).sendKeys(destination);
    }

    public void checkDestinationselected(String destination) {
        // Verify whether location is selected
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver ->
                driver.findElement(destinationField)
                        .getAttribute("value")
                        .toLowerCase()
                        .contains(destination.toLowerCase())
        );

        System.out.println(destination + " selected successfully");
    }

    By calendarField = By.xpath("//div[@aria-label=\"Select date of journey\" and @role=\"dialog\"]");
    By monthYear = By.xpath("//p[starts-with(@class,'monthYear')]");
    By dates = By.xpath("//div[starts-with(@class,'date')]/span");
    By nextMonth = By.xpath("//i[starts-with(@aria-label,'Next month')]");


    public void selectJourneyDate(String month, String day) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(calendarField)).click();
        while (true) {

            String currentMonth = driver.findElement(monthYear).getText();

            if (currentMonth.contains(month)) {

                List<WebElement> dateList = driver.findElements(dates);

                for (WebElement date : dateList) {

                    if (date.getText().trim().equals(day)) {
                        date.click();
                        return;
                    }
                }

            } else {
                wait.until(ExpectedConditions.elementToBeClickable(nextMonth)).click();
            }
        }
    }

    By searchButton = By.xpath("//button[@aria-label=\"Search buses\"]");
    public void clickSearchButton() throws InterruptedException {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

//    By destinationlist= By.xpath("//div[starts-with(@id,'suggestion-') and @role='button']");

    public void selectDestinationFromList(String dest) {

        By destinationlist = By.xpath(
                "//div[starts-with(@aria-label,'" + dest + "')]"
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement destination = wait.until(
                ExpectedConditions.elementToBeClickable(destinationlist)
        );

        destination.click();
    }

    By womenOnlyCheckbox= By.cssSelector("div[role='switch'][aria-label='Booking for women']");
    By gotit= By.cssSelector("button[aria-label=\"Got it\"]");
    public void selectWomenOnlyBooking() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(womenOnlyCheckbox));
        if (!checkbox.getAttribute("aria-checked").equals("true")) {
            checkbox.click();
        }
        //click on got it
        WebElement gotItButton = wait.until(ExpectedConditions.elementToBeClickable(gotit));
        gotItButton.click();
    }

    public void listOfBusFound() {
       System.out.println(driver.findElement(By.xpath("//div[starts-with(@class,\"busesFound\")]")).getText());
    }
}
