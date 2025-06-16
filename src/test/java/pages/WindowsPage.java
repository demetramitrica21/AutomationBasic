package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class WindowsPage extends BasePage {
    //locatori specifici paginii;
    private By pageTitle = By.xpath("//h1[@class]");
    private By newTabButton = By.id("tabButton");
    private By windowsTextValidationLocator = By.id("sampleHeading");
    private By newWindowButton = By.id("windowButton");
    private By newWindowMessageButton = By.id("messageWindowButton");

    public WindowsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "Browser Windows", "Page is not loaded properly");
    }

    public void interactWithNewTab(String expectedTextValue) {
        elementMethods.clickElement(newTabButton);
        // declaram o lista de ferestre: noi avem 2 ferestre;
        windowsMethods.switchToWindow(1);
        Assert.assertEquals(elementMethods.getTextFromElement(windowsTextValidationLocator), expectedTextValue, "Text is not displayed properly");
        //driver close iti inchide doar tab-ul si driver.quit inchide intreaga instanta, toate taburile;
        windowsMethods.closeWindowOrTab();
        //acum trebuie sa schimbam focusul;
        windowsMethods.switchToWindow(0);
    }

    public void interactWithNewWindow(String expectedTextValue) {
        elementMethods.clickElement(newWindowButton);
        windowsMethods.switchToWindow(1);
        Assert.assertEquals(elementMethods.getTextFromElement(windowsTextValidationLocator), expectedTextValue, "Text is not displayed properly");
        //driver close iti inchide doar tab-ul si driver.quit inchide intreaga instanta, toate taburile;
        windowsMethods.closeWindowOrTab();
        //acum trebuie sa schimbam focusul;
        windowsMethods.switchToWindow(0);
    }

    public void interactWithNewMessageWindow() {
        elementMethods.clickElement(newWindowMessageButton);
        // declaram o lista de ferestre: noi avem 2 ferestre;
        windowsMethods.newMessageWindow();
        windowsMethods.switchToWindow(1);
        windowsMethods.closeWindowOrTab();
        windowsMethods.switchToWindow(0);
    }
}
