package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class CommonPage extends BasePage {
    //locatori specifici paginii;
    private By subMenuListLocator = By.xpath("//span[@class='text']");

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        logInfo(PASS_STEP,"Validate that CommonPage is loaded properly");
        Assert.assertEquals(driver.getTitle(), "DEMOQA", "Page is not loaded properly");
    }

    public void goToDesiredSubMenu(String subMenuValue) {
        logInfo(INFO_STEP,"User choose desired SubMenu: " + subMenuValue);
        elementMethods.scrollPageDown("500");
        elementMethods.chooseElementFromListByText(subMenuListLocator, subMenuValue);
    }
}
