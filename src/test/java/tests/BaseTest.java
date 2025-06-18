package tests;

import extentUtility.ExtentHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import propertyUtility.PropertyUtility;

import static extentUtility.ExtentHelper.*;

public class BaseTest {

    WebDriver driver;
    public PropertyUtility propertyUtility;
    public String testName;

    @BeforeSuite
    public void initializeReport(){
        initiateReport();
    }

    @BeforeMethod
    //facem o metoda care deschide un browser;
    public void openBrowser() {
        driver = new ChromeDriver();
        // navigam catre pagine website-ului
        driver.get("https://demoqa.com/");
        //facem fereastra browser-ului maximize
        driver.manage().window().maximize();
        testName= this.getClass().getSimpleName();
        createTest(testName);
    }
    @AfterMethod
    public void closeBrowser(ITestResult results) {
        if(results.getStatus()==ITestResult.FAILURE){
            String errorMessage = results.getThrowable().getMessage();
            logFailScreenshot(driver,errorMessage);
        }
        if(driver!= null){
            driver.quit();
        }
        finishTest(testName);
    }

    @AfterSuite
    public void finalizeReport(){
        generateReport();
    }
}
