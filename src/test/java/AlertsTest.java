import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertsTest {
    WebDriver driver;

    @Test
    public void alertsTest() {
        openBrowser();
        chooseMenu();
        chooseSubMenu();
        interactWithFirstAlert();
        interactWithTimerAlert();
        interactWithConfirmAlert("Cancel");
        interactWithPromptBox("Demetra");
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
        WebElement alertsSubMenu = driver.findElement(By.xpath("//span[text()='Alerts']"));
        alertsSubMenu.click();
    }

    //facem o metoda care sa interactioneze cu prima alerta;
    public void interactWithFirstAlert() {
        WebElement firstAlertButton = driver.findElement(By.id("alertButton"));
        firstAlertButton.click();
        Alert FirstAlert = driver.switchTo().alert();
        FirstAlert.accept();
    }

    //facem o metoda care sa interactioneze cu prima alerta;
    public void interactWithTimerAlert() {
        WebElement timerAlertButton = driver.findElement(By.id("timerAlertButton"));
        timerAlertButton.click();
        //Inainte sa schimbam focusul pe alerta, trebuie sa punem un wait explicit;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert timerAlert = driver.switchTo().alert();
        timerAlert.accept();
    }

    public void interactWithConfirmAlert(String alertValue) {
        WebElement confirmAlertButton = driver.findElement(By.id("confirmButton"));
        confirmAlertButton.click();
        Alert confirmAlert = driver.switchTo().alert();
        if (alertValue.equals("ok")) {
            confirmAlert.accept();
            WebElement alertResultText = driver.findElement(By.id("confirmResult"));
            Assert.assertTrue(alertResultText.getText().contains(alertValue), "You didn't select Ok. You selected: " + alertResultText.getText());
        }
        if (alertValue.equals("Cancel")) {
            confirmAlert.dismiss();
            WebElement alertResultText = driver.findElement(By.id("confirmResult"));
            Assert.assertTrue(alertResultText.getText().contains(alertValue), "You didn't select Cancel. You selected: " + alertResultText.getText());
        }
    }
        public void interactWithPromptBox(String alertValue){
            WebElement confirmPromptButton = driver.findElement(By.id("promtButton"));
            confirmPromptButton.click();
            Alert promptAlert = driver.switchTo().alert();
            //introduce numele meu in casuta de prompt;
            promptAlert.sendKeys(alertValue);
            //apoi apasa pe butonul ok;
            promptAlert.accept();
            WebElement promptResult= driver.findElement(By.id("promptResult"));
            Assert.assertTrue(promptResult.getText().contains(alertValue),"You didn't enter the right name. In that box " + promptResult.getText());
        }
    }
