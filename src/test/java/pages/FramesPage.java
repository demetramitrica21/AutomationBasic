package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle),"Frames","Page is not loaded properly");
    }

    public void interactWithFrameOne(String expectedTextValue){
        //schimbare de focus pe frame(prima pagina);
        frameMethods.switchToFrame(frameOneLocator);
        Assert.assertEquals(elementMethods.getTextFromElement(frameTextValueLocator), expectedTextValue, "Text is not displayed properly");
        System.out.println("Frame one text is: " + elementMethods.getTextFromElement(frameTextValueLocator));
        frameMethods.switchToDefaultPage();
    }
    public void interactWithFrameTwo(String expectedTextValue){
        //schimbare de focus pe frame(prima pagina);
       frameMethods.switchToFrame(frameTwoLocator);
        Assert.assertEquals(elementMethods.getTextFromElement(frameTextValueLocator), expectedTextValue, "Text is not displayed properly");
        System.out.println("Frame two text is: " + elementMethods.getTextFromElement(frameTextValueLocator));
        frameMethods.switchToDefaultPage();
    }
}
