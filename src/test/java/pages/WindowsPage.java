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

    String expectedText = "This is a sample page";

    public WindowsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        Assert.assertEquals(driver.findElement(pageTitle).getText(), "Browser Windows", "Page is not loaded properly");
    }

    public void interactWithNewTab() {
        driver.findElement(newTabButton).click();
        // declaram o lista de ferestre: noi avem 2 ferestre;
        List<String> windowsList = new ArrayList<>(driver.getWindowHandles());
        //noua ne trebuie focus pe al doilea tab, care are indexul 1;
        driver.switchTo().window(windowsList.get(1));
        //identificam elementul pe care este textul;
        Assert.assertEquals(driver.findElement(windowsTextValidationLocator).getText(), expectedText, "Text is not displayed properly");
        //driver close iti inchide doar tab-ul si driver.quit inchide intreaga instanta, toate taburile;
        driver.close();
        //acum trebuie sa schimbam focusul;
        driver.switchTo().window(windowsList.get(0));
    }

    public void interactWithNewWindow() {
        driver.findElement(newWindowButton).click();
        // declaram o lista de ferestre: noi avem 2 ferestre;
        List<String> windowsList = new ArrayList<>(driver.getWindowHandles());
        //noua ne trebuie focus pe al doilea tab, care are indexul 1;
        driver.switchTo().window(windowsList.get(1));
        Assert.assertEquals(driver.findElement(windowsTextValidationLocator).getText(), expectedText, "Text is not displayed properly");
        //driver close iti inchide doar tab-ul si driver.quit inchide intreaga instanta, toate taburile;
        driver.close();
        //acum trebuie sa schimbam focusul;
        driver.switchTo().window(windowsList.get(0));
    }

    public void interactWithNewMessageWindow() {
        driver.findElement(newWindowMessageButton).click();
        // declaram o lista de ferestre: noi avem 2 ferestre;
        List<String> windowsList = new ArrayList<>(driver.getWindowHandles());
        //noua ne trebuie focus pe al doilea tab, care are indexul 1;
        if (windowsList.size() > 1) {
            System.out.println("A new window is successfully opened");
        } else {
            System.out.println("New window cannot be opened");
        }
        driver.switchTo().window(windowsList.get(1));
        driver.close();
        driver.switchTo().window(windowsList.get(0));
    }
}
