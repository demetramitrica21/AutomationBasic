import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WindowsTest {
    WebDriver driver;

    @Test
    public void windowsTest() {
        openBrowser();
        chooseMenu();
        chooseSubMenu();
        interactWithNewTab();
        interactWithNewWindow();
        interactWithNewMessageWindow();
//        closeBrowser();
    }

    //facem o metoda care deschide un browser;
    public void openBrowser() {
        driver = new ChromeDriver();
        // navigam catre pagine website-ului
        driver.get("https://demoqa.com/");
        //facem fereastra browser-ului maximize
        driver.manage().window().maximize();
    }
    //facem o metoda care alege un meniu;

    public void chooseMenu() {
        //identificam meniul dorit si facem click pe el;
        WebElement alertsWindowsAndFramesMenu = driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));
        //actionam butonul pe meniul de mai sus;
        // facem scroll pana in dreptul elementului pe care vrem sa actionam;
        scrollIntoElement(alertsWindowsAndFramesMenu);
        alertsWindowsAndFramesMenu.click();
    }
    //facem o metoda care sa faca scroll;

    public void scrollIntoElement(WebElement alertsMenu) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", alertsMenu);
    }

    //facem o metoda care sa selecteze submeniul;
    public void chooseSubMenu() {
        //identificam submeniul dorit si facem click pe el;
        WebElement alertsSubMenu = driver.findElement(By.xpath("//span[text()='Browser Windows']"));
        alertsSubMenu.click();
    }

    public void interactWithNewTab() {
        WebElement newTabButton = driver.findElement(By.id("tabButton"));
        newTabButton.click();
        // declaram o lista de ferestre: noi avem 2 ferestre;
        List<String> windowsList = new ArrayList<>(driver.getWindowHandles());
        //noua ne trebuie focus pe al doilea tab, care are indexul 1;
        driver.switchTo().window(windowsList.get(1));
        //identificam elementul pe care este textul;
        WebElement tabTextValue = driver.findElement(By.id("sampleHeading"));
        String expectedText = "This is a sample page";
        Assert.assertEquals(tabTextValue.getText(), expectedText, "Text is not displayed properly");
        //driver close iti inchide doar tab-ul si driver.quit inchide intreaga instanta, toate taburile;
        driver.close();
        //acum trebuie sa schimbam focusul;
        driver.switchTo().window(windowsList.get(0));
    }

    public void interactWithNewWindow() {
        WebElement newWindowButton = driver.findElement(By.id("windowButton"));
        newWindowButton.click();
        // declaram o lista de ferestre: noi avem 2 ferestre;
        List<String> windowsList = new ArrayList<>(driver.getWindowHandles());
        //noua ne trebuie focus pe al doilea tab, care are indexul 1;
        driver.switchTo().window(windowsList.get(1));
        //identificam elementul pe care este textul;
        WebElement windowTextValue = driver.findElement(By.id("sampleHeading"));
        String expectedText = "This is a sample page";
        Assert.assertEquals(windowTextValue.getText(), expectedText, "Text is not displayed properly");
        //driver close iti inchide doar tab-ul si driver.quit inchide intreaga instanta, toate taburile;
        driver.close();
        //acum trebuie sa schimbam focusul;
        driver.switchTo().window(windowsList.get(0));
    }

    public void closeBrowser() {
        driver.quit();
    }
    public void interactWithNewMessageWindow() {
        WebElement newWindowMessageButton = driver.findElement(By.id("messageWindowButton"));
        newWindowMessageButton.click();
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
