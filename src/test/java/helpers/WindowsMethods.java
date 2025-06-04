package helpers;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class WindowsMethods {

    WebDriver driver;

    public WindowsMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToWindow(int index){
        List<String> windowsList = new ArrayList<>(driver.getWindowHandles());
        //noua ne trebuie focus pe al doilea tab, care are indexul 1;
        driver.switchTo().window(windowsList.get(index));
    }

    public void closeWindowOrTab(){
        driver.close();
    }

    public void newMessageWindow(){
        List<String> windowsList = new ArrayList<>(driver.getWindowHandles());
        //noua ne trebuie focus pe al doilea tab, care are indexul 1;
        if (windowsList.size() > 1) {
            System.out.println("A new window is successfully opened");
        } else {
            System.out.println("New window cannot be opened");
        }
    }
}
