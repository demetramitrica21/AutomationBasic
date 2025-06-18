package pages;

import extentUtility.ExtentHelper;
import extentUtility.ReportEventType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class HomePage extends BasePage{

    //locatori specifici paginii;
    private By pageTitle= By.xpath("//img[@alt='Selenium Online Training']");
    private By menuListLocator= By.xpath("//h5");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        logInfo(PASS_STEP,"Validate that Homepage is loaded properly");
        Assert.assertEquals(elementMethods.getElement(pageTitle).getDomAttribute("alt"),
                "Selenium Online Training","Page is not loaded properly");
    }

    public void goToDesiredMenu(String menuValue) {
        logInfo(INFO_STEP,"User choose desired Menu: " + menuValue);
        elementMethods.scrollPageDown("400");
        elementMethods.chooseElementFromListByText(menuListLocator,menuValue);
    }
}
