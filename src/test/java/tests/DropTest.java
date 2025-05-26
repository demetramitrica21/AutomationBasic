package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropTest extends BaseTest {

    @Test
    public void droppableTest() {
        chooseMenu();
        chooseSubMenu();
        pickAndDropElement();
//        closeBrowser();
    }

    //facem o metoda care deschide un browser;
//    public void openBrowser() {
//        driver = new ChromeDriver();
//        // navigam catre pagine website-ului
//        driver.get("https://demoqa.com/");
//        //facem fereastra browser-ului maximize
//        driver.manage().window().maximize();
//    }

    //facem o metoda care alege un meniu;
    public void chooseMenu() {
        //identificam meniul dorit si facem click pe el;
        WebElement alertsWindowsAndFramesMenu = driver.findElement(By.xpath("//h5[text()='Interactions']"));
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
        WebElement alertsSubMenu = driver.findElement(By.xpath("//span[text()='Droppable']"));
        alertsSubMenu.click();
    }
//    public void closeBrowser() {
//        driver.quit();
//    }
    public void pickAndDropElement(){
        WebElement draggableElement = driver.findElement(By.id("draggable"));
        WebElement droppableElement = driver.findElement(By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']"));
        String initialTargetText= droppableElement.getText(); //am salvat textul de pe element intr-un string;
        Actions action = new Actions(driver);
        //se ia elementul care se trage si se lasa in Element;
        action.dragAndDrop(draggableElement,droppableElement).release().perform();
        Assert.assertNotEquals(droppableElement.getText(),initialTargetText,"Initial text is the same with actual text after element dropped");
        System.out.println("Initial text is: " + initialTargetText + " Text after successful drop: " + droppableElement.getText());
    }
}

