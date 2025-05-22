package Homework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WebsiteOne {

    WebDriver driver;

    //initializare browser; gasit un element sau 2; // facut un click, un sendkeys pe elemente;

    @Test

    public void practiceWebsiteTest() throws InterruptedException {

        driver = new ChromeDriver();
        //navigam catre pagina NOTINO;
        driver.get("https://www.notino.ro/");
        //maximizam fereastra care se deschide;
        driver.manage().window().maximize();
        //facem scroll pana in dreptul elementului pe care vrem sa actionam;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Scroll down treptat;
        for (int i = 0; i < 15; i++) {
            js.executeScript("window.scrollBy(0,400)");
            // asteapta 500ms intre scroll-uri;
            Thread.sleep(500);
        }
        //scroll up ;
        for (int i = 0; i < 15; i++) {
            js.executeScript("window.scrollBy(0,-400)");
            // asteapta 300ms intre scroll-uri;
            Thread.sleep(300);
            //scroll up ;
        }
//        WebElement ziuaMameiMenu= driver.findElement(By.xpath("//span[text()='Ziua Mamei']"));
//        ziuaMameiMenu.click();
        WebElement accountIcon = driver.findElement(By.xpath("//svg[@data-testid='my-notino-icon']"));
        accountIcon.click();
//        WebElement userNameFieldLogIn= driver.findElement(By.id("Email"));
//        userNameFieldLogIn.sendKeys("demetra_roman@yahoo.com");
//        WebElement passwordFieldLogIn= driver.findElement(By.id("Password"));
//        passwordFieldLogIn.sendKeys("rq5abVfv73$RZ@/");
    }
}
