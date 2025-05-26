package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomePage extends BasePage{

    //locatori specifici paginii;
    private By alertsWindowsAndFramesMenu= By.xpath("//h5[text()='Alerts, Frame & Windows']");
    private By pageTitle= By.xpath("//img[@alt='Selenium Online Training']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void chooseMenu() {
        //actionam butonul pe meniul de mai sus;
        // facem scroll pana in dreptul elementului pe care vrem sa actionam;
        scrollIntoElement(driver.findElement(alertsWindowsAndFramesMenu));
        driver.findElement(alertsWindowsAndFramesMenu).click();
    }
    public void scrollIntoElement(WebElement alertsMenu) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", alertsMenu);
    }

    @Override
    public void isPageLoaded() {
        Assert.assertEquals(driver.findElement(pageTitle).getDomAttribute("alt"),
                "Selenium Online Training","Page is not loaded properly");
    }
}
