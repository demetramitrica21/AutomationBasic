package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

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
        Assert.assertEquals(driver.findElement(pageTitle).getText(),"Alerts","Page is not loaded properly");
    }

    public void interactWithFirstAlert() {
        driver.findElement(firstAlertButton).click();
        Alert FirstAlert = driver.switchTo().alert();
        FirstAlert.accept();
    }

    //facem o metoda care sa interactioneze cu prima alerta;
    public void interactWithTimerAlert() {
        driver.findElement(timerAlertButton).click();
        //Inainte sa schimbam focusul pe alerta, trebuie sa punem un wait explicit;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert timerAlert = driver.switchTo().alert();
        timerAlert.accept();
    }

    public void interactWithConfirmAlert(String alertValue) {
        driver.findElement(confirmAlertButton).click();
        Alert confirmAlert = driver.switchTo().alert();
        if (alertValue.equals("ok")) {
            confirmAlert.accept();
            Assert.assertTrue(driver.findElement(alertResultText).getText().contains(alertValue), "You didn't select Ok. You selected: "
                    + driver.findElement(alertResultText).getText());
        }
        if (alertValue.equals("Cancel")) {
            confirmAlert.dismiss();
            Assert.assertTrue(driver.findElement(alertResultText).getText().contains(alertValue), "You didn't select Cancel. You selected: "
                    + driver.findElement(alertResultText).getText());
        }
    }
    public void interactWithPromptBox(String alertValue){
        driver.findElement(confirmPromptButton).click();
        Alert promptAlert = driver.switchTo().alert();
        //introduce numele meu in casuta de prompt;
        promptAlert.sendKeys(alertValue);
        //apoi apasa pe butonul ok;
        promptAlert.accept();
        Assert.assertTrue(driver.findElement(promptResult).getText().contains(alertValue),"You didn't enter the right name. In that box "
                + driver.findElement(promptResult).getText());
    }
}

