package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class DroppablePage extends BasePage {
    //locatori specifici;
    private By pageTitle = By.xpath("//h1[@class]");
    private By draggableElement = By.id("draggable");
    private By droppableElement = By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']");

    public DroppablePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        Assert.assertEquals(driver.findElement(pageTitle).getText(), "Droppable", "Page is not loaded properly");
    }

    public void pickAndDropElement() {
        String initialTargetText = driver.findElement(droppableElement).getText(); //am salvat textul de pe element intr-un string;
        Actions action = new Actions(driver);
        //se ia elementul care se trage si se lasa in Element;
        action.dragAndDrop(driver.findElement(draggableElement), driver.findElement(droppableElement)).release().perform();
        Assert.assertNotEquals(driver.findElement(droppableElement).getText(), initialTargetText, "Initial text is the same with actual text after element dropped");
        System.out.println("Initial text is: " + initialTargetText + " Text after successful drop: " + driver.findElement(droppableElement).getText());
    }
}
