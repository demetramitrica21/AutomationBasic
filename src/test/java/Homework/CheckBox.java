package Homework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class CheckBox {
    //de facut din Elements ---> Check Box
    WebDriver driver;


    @Test

    public void checkBoxMethod() throws InterruptedException {
        openBrowser();
        chooseMenu();
        chooseSubMenu();
//        expandCheckBoxList();
        //collapseCheckBoxList();
        scrollDownPageAfterClickingExpandButton();
        toggleHomeArrow();
        toggleDesktopArrow();
        toggleDocumentsArrow();
        toggleDownloadsArrow();
        selectCheckBoxDesktop();
        validateIfTheResultContainsTheRightText();
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
        WebElement elementsMenu = driver.findElement(By.xpath("//h5[text()='Elements']"));
        //actionam butonul pe meniul de mai sus;
        // facem scroll pana in dreptul elementului pe care vrem sa actionam;
        scrollIntoElement(elementsMenu);
        elementsMenu.click();
    }
    //facem o metoda care sa faca scroll;

    public void scrollIntoElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //facem o metoda care sa aleaga submeniul check-box;

    public void chooseSubMenu() {
        //identificam meniul dorit si facem click pe el;
        WebElement checkBoxSubMenu = driver.findElement(By.xpath("//span[text()='Check Box']"));
        checkBoxSubMenu.click();
    }

    // facem o metoda care sa ne deschida toata lista de la cele butonul "+" din dreapta sus a submeniului Check Box;
//    public void expandCheckBoxList() {
//        WebElement expandButton = driver.findElement(By.xpath("//button[@title='Expand all']"));
//        expandButton.click();
//    }

    // facem o metoda care sa ne inchida toata lista de la cele butonul "-" din dreapta sus a submeniului Check Box;
//    public void collapseCheckBoxList() {
//        WebElement collapseButton = driver.findElement(By.xpath("//button[@title='Collapse all']"));
//        collapseButton.click();
//    }
    public void scrollDownPageAfterClickingExpandButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)");
    }

    //aceasta metoda da click pe sageata dinaintea check-boxului pentru a expanda lista pt Home;
    public void toggleHomeArrow() {
        WebElement toggleHome = driver.findElement(By.xpath("//span[text()='Home']/ancestor::label/preceding-sibling::button"));
        toggleHome.click();
    }


    //aceasta metoda da click pe sageata dinaintea check-boxului pentru a expanda lista pt Desktop;
    public void toggleDesktopArrow() {
        WebElement toggleDesktop = driver.findElement(By.xpath("//span[text()='Desktop']/ancestor::label/preceding-sibling::button"));
        toggleDesktop.click();
    }

   //aceasta metoda da click pe sageata dinaintea check-boxului pentru a expanda lista pt Documents;
    public void toggleDocumentsArrow() {
        WebElement toggleDocuments = driver.findElement(By.xpath("//span[text()='Documents']/ancestor::label/preceding-sibling::button"));
        toggleDocuments.click();
    }

    //    //aceasta metoda da click pe sageata dinaintea check-boxului pentru a expanda lista pt Downloads;
    public void toggleDownloadsArrow() {
        WebElement toggleDownloads = driver.findElement(By.xpath("//span[text()='Downloads']/ancestor::label/preceding-sibling::button"));
        toggleDownloads.click();
    }

    //facem o metoda care sa selecteze pe rand toate campurile din sub-meniul Check-Box;
    public void selectCheckBoxDesktop() {
        WebElement checkBoxDesktop = driver.findElement(By.xpath("//span[@class='rct-checkbox']"));
        checkBoxDesktop.click();
    }

    public void validateIfTheResultContainsTheRightText() {
        // Preluăm textul rezultatului afișat în josul paginii
        WebElement result = driver.findElement(By.id("result"));
        //cream un Strig cu rezultatul;
        String resultText = result.getText();
        //ne asiguram ca intre campurile selectate se afla si "desktop";
        Assert.assertTrue(resultText.toLowerCase().contains("general"), "Rezultatul nu contine 'general'");
        Assert.assertTrue(resultText.toLowerCase().contains("downloads"), "Rezultatul nu contine 'downloads'");
    }
}

