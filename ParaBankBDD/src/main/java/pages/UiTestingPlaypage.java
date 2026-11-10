package pages;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UiTestingPlaypage {
    private WebDriver driver;
    // Constructor
    public UiTestingPlaypage(WebDriver driver) {
        this.driver = driver;
    }
    By advancedButton = By.xpath("//button[contains(text(),'Advanced')]");
    By proceedLink = By.id("proceed-link");
    //open the page
    public void openPage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        //implicit wait
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        driver.findElement(advancedButton).click();
        driver.findElement(proceedLink).click();
    }
    By delayButton = By.xpath("//a[contains(text(),'Load Delay')]");

    public void clickLoadDelayButton() {
        WebElement element = driver.findElement(delayButton);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );

        element.click();
    }

    //after dalay check //button[contains(text(),'Button Appearing After Delay')] button present
    By afterdelayButton = By.xpath("//button[contains(text(),'Button Appearing After Delay')]");
    public boolean isButtonAppearingAfterDelayPresent() {
        return driver.findElements(afterdelayButton).size() > 0;
    }

    //click on the button Appearing After Delay
    public void clickButtonAppearingAfterDelay() {
       driver.findElement(afterdelayButton).click();
    }

    By progressbar= By.xpath("//a[text()=\"Progress Bar\"]");
    public void clickProgressBarButton() {
        WebElement element = driver.findElement(progressbar);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );

        element.click();

    }

    By startButton= By.id("startButton");
    By stopButton= By.id("stopButton");
    By progressBar= By.id("progressBar");
    public void startandstopProgressBar() {
        driver.findElement(startButton).click();
        //wait for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(stopButton).click();
        System.out.println(driver.findElement(progressBar).getText());
    }

    By alertslink= By.xpath("//a[text()=\"Alerts\"]");
    public void clickAlertslink() {
        WebElement element = driver.findElement(alertslink);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );

        element.click();
    }
    By alertButton= By.id("alertButton");
    By confirmButton= By.id("confirmButton");
    By promptButton= By.id("promptButton");
    public void clickandacceptAlertButton() {
        driver.findElement(alertButton).click();
        //click on alert and accept it
        driver.switchTo().alert().accept();
    }

    public void clickandacceptConfirmButton() {
        driver.findElement(confirmButton).click();
        //click on confirm and accept it
        driver.switchTo().alert().accept();
    }

    public void clickandacceptPromptButton() {
        driver.findElement(promptButton).click();
        //enter text in prompt and accept it
        driver.switchTo().alert().sendKeys("Test");
        driver.switchTo().alert().accept();
    }


}
