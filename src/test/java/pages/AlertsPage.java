package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class AlertsPage extends BasePage{
    private By pageTitle= By.xpath("//h1[@class]");
    private By firstAlertButton= By.id("alertButton");
    private By timerAlertButton= By.id("timerAlertButton");
    private By confirmAlertButton=By.id("confirmButton");
    private By alertResultText=By.id("confirmResult");
    private By confirmPromptButton=By.id("promtButton");
    private By promptResult=By.id("promptResult");

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        logInfo(PASS_STEP,"Validate that AlertPage is loaded properly");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle),"Alerts","Page is not loaded properly");
    }

    public void interactWithFirstAlert() {
        logInfo(INFO_STEP,"User interacts with first alert");
        logInfo(INFO_STEP,"User clicks on first alert button");
        elementMethods.clickElement(firstAlertButton);
        logInfo(INFO_STEP,"User accepts alert");
        alertsMethods.alertOk();
    }

    //facem o metoda care sa interactioneze cu prima alerta;
    public void interactWithTimerAlert() {
        logInfo(INFO_STEP,"User interacts with timer alert");
        logInfo(INFO_STEP,"User clicks on timer alert button");
        elementMethods.clickElement(timerAlertButton);
        //Inainte sa schimbam focusul pe alerta, trebuie sa punem un wait explicit;
        logInfo(INFO_STEP,"User accepts timer alert");
        alertsMethods.timerAlert();
    }

    public void interactWithConfirmAlert(String alertValue) {
        logInfo(INFO_STEP,"User interacts with confirm alert");
        logInfo(INFO_STEP,"User clicks on confirm alert button");
        elementMethods.clickElement(confirmAlertButton);
        alertsMethods.confirmAlert(alertValue);
        logInfo(INFO_STEP,"User choose: " + alertValue);
        logInfo(PASS_STEP,"Validate that the value we choose: " + alertValue +
                " is properly displayed in the results label");
        Assert.assertTrue(elementMethods.getTextFromElement(alertResultText).contains(alertValue), "You didn't select Ok. You selected: "
                + elementMethods.getTextFromElement(alertResultText));
    }
    public void interactWithPromptBox(String alertValue){
        logInfo(INFO_STEP,"User interacts with prompt alert");
        logInfo(INFO_STEP,"User clicks on prompt alert button");
        elementMethods.clickElement(confirmPromptButton);
        alertsMethods.promptAlert(alertValue);
        logInfo(INFO_STEP,"User choose: " + alertValue);
        logInfo(PASS_STEP,"Validate that the value we choose: " + alertValue +
                " is properly displayed in the results label");
        Assert.assertTrue(elementMethods.getTextFromElement(promptResult).contains(alertValue),"You didn't enter the right name. In that box "
                + elementMethods.getTextFromElement(promptResult));
    }
}

