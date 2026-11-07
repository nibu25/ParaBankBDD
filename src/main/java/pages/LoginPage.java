package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By usernameField= By.xpath("//input[@name=\"username\"]");
    private By passwordField= By.xpath("//input[@name=\"password\"]");
    private By loginButton = By.xpath("//input[@type=\"submit\"]");

    public void openLoginPage(String url) {
        driver.get(url);
    }

    public void enterCredentials(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    // Check account overview
    public boolean isAccountOverviewDisplayed() {
        return driver.findElements(
                By.xpath("//h1[contains(normalize-space(.), 'Accounts Overview')]")
        ).size() > 0;
    }

}
