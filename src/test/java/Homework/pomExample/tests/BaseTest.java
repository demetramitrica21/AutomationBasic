package Homework.pomExample.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    //de facut un exemplu cu un scenariu de test folosind POM
    //BaseTest+ clasa de test
    //BasePage+ clasa de page

    WebDriver driver;

    @BeforeMethod
    //facem o metoda care deschide un browser-ul;
    public void openBrowser() {
        driver = new ChromeDriver();
        // navigam catre pagine website-ului
        driver.get("https://www.anasofia.ro/");
        //facem fereastra browser-ului maximize
        driver.manage().window().maximize();
    }
    @AfterMethod
    //facem o metoda care inchide browser-ul;
    public void closeBrowser() {
        driver.quit();
    }
}
