package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FrameMethods {
    WebDriver driver;

    public FrameMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToFrame(By locator){
        driver.switchTo().frame(driver.findElement(locator));
    }

    public void switchToDefaultPage(){
        driver.switchTo().defaultContent();
    }
}
