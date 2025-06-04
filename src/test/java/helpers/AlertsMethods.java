package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsMethods {

    WebDriver driver;

    public AlertsMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public Alert switchToAlert() {
        return driver.switchTo().alert();
    }

    public void alertOk() {
        waitForAlert();
        Alert alertOk = switchToAlert();
        alertOk.accept();
    }

    public void timerAlert() {
        waitForAlert();
        Alert timerAlert = switchToAlert();
        timerAlert.accept();
    }

    public void confirmAlert(String alertValue) {
        Alert confirmAlert = switchToAlert();
        switch (alertValue) {
            case "OK":
                confirmAlert.accept();
                break;
            case "Cancel":
                confirmAlert.dismiss();
                break;
        }
    }

    public void promptAlert(String textValue) {
        waitForAlert();
        Alert promptAlert = switchToAlert();
        promptAlert.sendKeys(textValue);
        promptAlert.accept();
    }
}
