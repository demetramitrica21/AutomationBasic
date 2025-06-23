package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class FramesPage extends BasePage {
    //locatori specifici paginii;
    private By pageTitle= By.xpath("//h1[@class]");
    private By frameOneLocator= By.id("frame1");
    private By frameTextValueLocator= By.id("sampleHeading");
    private By frameTwoLocator= By.id("frame2");


    public FramesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        logInfo(PASS_STEP,"Validate that FramesPage is loaded properly");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle),"Frames","Page is not loaded properly");
    }

    public void interactWithFrameOne(String expectedTextValue){
        //schimbare de focus pe frame(prima pagina);
        logInfo(INFO_STEP,"User interacts with first frame");
        logInfo(INFO_STEP,"User switch to first frame");
        frameMethods.switchToFrame(frameOneLocator);
        logInfo(PASS_STEP,"Validate that the text from first frame is the one that we expect: " + expectedTextValue);
        Assert.assertEquals(elementMethods.getTextFromElement(frameTextValueLocator), expectedTextValue, "Text is not displayed properly");
        System.out.println("Frame one text is: " + elementMethods.getTextFromElement(frameTextValueLocator));
        frameMethods.switchToDefaultPage();
    }
    public void interactWithFrameTwo(String expectedTextValue){
        //schimbare de focus pe frame(prima pagina);
        logInfo(INFO_STEP,"User interacts with second frame");
        logInfo(INFO_STEP,"User switch to second frame");
        frameMethods.switchToFrame(frameTwoLocator);
        logInfo(PASS_STEP,"Validate that the text from second frame is the one that we expect: " + expectedTextValue);
        Assert.assertEquals(elementMethods.getTextFromElement(frameTextValueLocator), expectedTextValue, "Text is not displayed properly");
        System.out.println("Frame two text is: " + elementMethods.getTextFromElement(frameTextValueLocator));
        frameMethods.switchToDefaultPage();
    }
}
