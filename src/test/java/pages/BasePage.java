package pages;

import helpers.AlertsMethods;
import helpers.ElementMethods;
import helpers.FrameMethods;
import helpers.WindowsMethods;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    public WebDriver driver;
    public ElementMethods elementMethods;
    public AlertsMethods alertsMethods;
    public FrameMethods frameMethods;
    public WindowsMethods windowsMethods;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        elementMethods = new ElementMethods(driver);
        alertsMethods = new AlertsMethods(driver);
        frameMethods = new FrameMethods(driver);
        windowsMethods = new WindowsMethods(driver);
    }

    public abstract void isPageLoaded();
}
