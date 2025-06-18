package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class ElementMethods {

    public WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions action;

    public ElementMethods(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }

    public void scrollPageDown(String scrollValue) {
        js.executeScript("window.scrollBy(0," + scrollValue + ")");
    }

    public WebElement getElement(By locator){
//        waitForElement(locator);
        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator){
        return driver.findElements(locator);
    }

    public void waitForElement(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(By locator){
        waitForElement(locator);
        getElement(locator).click();
    }

    public void clickElement(WebElement element){
        waitForElement(element);
        element.click();
    }

    public void fillElement(By locator, String textValue){
        waitForElement(locator);
        getElement(locator).clear();
        getElement(locator).sendKeys(textValue);
    }

    public void fillElement(WebElement element, String textValue){
        waitForElement(element);
        element.clear();
        element.sendKeys(textValue);
    }

    public void fillElement(By locator, Keys keyValue){
        waitForElement(locator);
        getElement(locator).sendKeys(keyValue);
    }

    public void fillElement(WebElement element, Keys keyValue){
        waitForElement(element);
        element.sendKeys(keyValue);
    }

    public void chooseElementFromListByText(By locator, String textValue){
        for (WebElement element : getElements(locator)) {
            if (element.getText().equals(textValue)) {
                element.click();
                break;
            }
        }
    }

    public void chooseElementFromListByText(List<WebElement> elementsList, String textValue){
        for (WebElement element : elementsList) {
            if (element.getText().equals(textValue)) {
                element.click();
                break;
            }
        }
    }

    public void pickAndDropElement(By actual, By next){
        waitForElement(actual);
        action.clickAndHold(getElement(actual)).moveToElement(getElement(next)).release().build().perform();
    }

    public String getTextFromElement(By locator){
        waitForElement(locator);
        return getElement(locator).getText();
    }

    public void selectElementByText(By locator, String textValue){
        waitForElement(locator);
        Select selectElement = new Select(driver.findElement(locator));
        selectElement.selectByVisibleText(textValue);
    }

    public void uploadDocument(By locator, String pathValue){
        String pictureFilePaths = "src/test/resources/picture/" + pathValue;
        File file = new File(pictureFilePaths);
        getElement(locator).sendKeys(file.getAbsolutePath());
    }

    public void setWait(Long miliSeconds){
        try {
            wait(miliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
