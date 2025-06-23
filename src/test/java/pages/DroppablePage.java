package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.time.Duration;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

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
        logInfo(PASS_STEP,"Validate that DroppablePage is loaded properly");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "Droppable", "Page is not loaded properly");
    }

    public void pickAndDropElement() {
        logInfo(INFO_STEP,"Pick and drop element to a specific target");
        elementMethods.scrollPageDown("300");
        String initialTargetText = elementMethods.getTextFromElement(droppableElement); //am salvat textul de pe element intr-un string;
        logInfo(INFO_STEP,"Initial target text is: " + initialTargetText);
        elementMethods.pickAndDropElement(draggableElement,droppableElement);
        String finalTargetText= elementMethods.getTextFromElement(droppableElement);
        logInfo(PASS_STEP,"Validate that element was dropped successfully -> final target text is:  " + finalTargetText);
        Assert.assertNotEquals(finalTargetText, initialTargetText, "Initial text is the same with actual text after element dropped");
        System.out.println("Initial text is: " + initialTargetText + " Text after successful drop: " + finalTargetText);
    }
}
