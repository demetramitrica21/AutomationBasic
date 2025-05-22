import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FramesTest {
    WebDriver driver;

    @Test
    public void framesTest() {
        openBrowser();
        chooseMenu();
        chooseSubMenu();
        interactWithFrameOne();
        interactWithFrameTwo();
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
        WebElement alertsSubMenu = driver.findElement(By.xpath("//span[text()='Frames']"));
        alertsSubMenu.click();
    }

    public void closeBrowser() {
        driver.quit();
    }
    public void interactWithFrameOne(){
        WebElement frameOneElement= driver.findElement(By.id("frame1"));
        //schimbare de focus pe frame(prima pagina);
        driver.switchTo().frame(frameOneElement);
        WebElement frameOneTextValue = driver.findElement(By.id("sampleHeading"));
        String expectedText = "This is a sample page";
        Assert.assertEquals(frameOneTextValue.getText(), expectedText, "Text is not displayed properly");
        System.out.println("Frame one text is: " + frameOneTextValue.getText());
        driver.switchTo().defaultContent(); //shimbam focusul pe pagina initiala;
    }
    public void interactWithFrameTwo(){
        WebElement frameTwoElement= driver.findElement(By.id("frame2"));
        //schimbare de focus pe frame(prima pagina);
        driver.switchTo().frame(frameTwoElement);
        WebElement frameTwoTextValue = driver.findElement(By.id("sampleHeading"));
        String expectedText = "This is a sample page";
        Assert.assertEquals(frameTwoTextValue.getText(), expectedText, "Text is not displayed properly");
        System.out.println("Frame two text is: " + frameTwoTextValue.getText());
        driver.switchTo().defaultContent(); //shimbam focusul pe pagina initiala;
    }
}
