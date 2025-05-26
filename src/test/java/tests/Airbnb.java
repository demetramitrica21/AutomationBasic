package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Airbnb {
    WebDriver driver;

    @Test
    public void airbnbTests() throws InterruptedException {
        openBrowser();
        acceptCookiesPolicy();
        searchDestinationFieldInput();
        selectDataToBookADestination();
        selectNumberOfGuests();
        enterSearchButton();
        createANewAccount();
    }

    //facem o metoda care deschide un browser;
    public void openBrowser() {
        driver = new ChromeDriver();
        // navigam catre pagine website-ului
        driver.get("https://www.airbnb.com.ro/");
        //facem fereastra browser-ului maximize
        driver.manage().window().maximize();
    }

    public void acceptCookiesPolicy() {
        //inainte de a apasa pe butonul Ok, trebuie sa ne concentram pe cookie banner si folosim wait until element to be clickable;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
        acceptCookies.click();
    }

    public void searchDestinationFieldInput() {
        WebElement destinationInput = driver.findElement(By.id("bigsearch-query-location-input"));
        destinationInput.sendKeys("Brașov,Poiana Brașov");
        //acum apare un drop down list si facem click pe una dintre variante;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement desiredDestination = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Poiana Brașov, Brașov, Brașov County']")));
        desiredDestination.click();
    }

    public void selectDataToBookADestination() {
        WebElement selectCheckInData = driver.findElement(By.xpath("//button[@data-state--date-string='2025-05-16']"));
        selectCheckInData.click();
        WebElement selectCheckOutData = driver.findElement(By.xpath("//button[@data-state--date-string='2025-05-19']"));
        selectCheckOutData.click();
    }

    public void selectNumberOfGuests() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement guestsField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='structured-search-input-field-guests-button']")));
        guestsField.click();
        WebElement numberOfAdults = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='stepper-adults-increase-button']")));
        numberOfAdults.click();
        numberOfAdults.click();
    }

    public void enterSearchButton() {
        WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='structured-search-input-search-button']"));
        searchButton.click();
    }
    public void createANewAccount() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement accountIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='cypress-headernav-profile']")));
        accountIcon.click();
        WebElement connectButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-testid='cypress-headernav-login']")));
        connectButton.click();
//        Thread.sleep(500);
//        WebElement addPhoneNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("phoneInputphone-login")));
//        addPhoneNumber.click();
//        addPhoneNumber.sendKeys("0756950504");
    }
}
