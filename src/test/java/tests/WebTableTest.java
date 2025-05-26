package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WebTableTest extends BaseTest {

    public int initialTableSize = 0;
    String firstName= "Firicel";
    String lastName= "Celentano";
    String email= "test@gmail.com";
    String age= "25";
    String salary= "100000";
    String department= "Testing";

    @Test
    public void webTableTest() {
        chooseMenu();
        chooseSubMenu();
        getTableSize();
        clickToAddNewRecord();
        fillFormValues();
        validateThatNewRecordsAreAddedProperly();
    }
    //facem o metoda care deschide un browser;

//    public void openBrowser() {
//        driver = new ChromeDriver();
//        // navigam catre pagine website-ului
//        driver.get("https://demoqa.com/");
//        //facem fereastra browser-ului maximize
//        driver.manage().window().maximize();
//    }

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
    //facem o metoda care sa selecteze submeniul;

    public void chooseSubMenu() {
        //identificam submeniul dorit si facem click pe el;
        WebElement webTableSubMenu = driver.findElement(By.xpath("//span[text()='Web Tables']"));
        webTableSubMenu.click();
    }

    //facem o metoda care sa ia numarul initial de randuri din table;

    public int getTableSize() {
        List<WebElement> tableRowList = driver.findElements(By.xpath("//div[@class='rt-tbody']//div[@class='rt-tr -odd' or @class='rt-tr -even']"));
        initialTableSize = tableRowList.size();
        System.out.println("Numarul initial de randuri in tabel este: " + initialTableSize);
        return initialTableSize;
    }

    //facem o metoda noua care adauga rand nou in tabel, dand click pe ADD;

    public void clickToAddNewRecord() {
        WebElement addNewRecordButton = driver.findElement(By.id("addNewRecordButton"));
        addNewRecordButton.click();
    }

    //facem o metoda care sa completeze toate campurile din formular;

    public void fillFormValues() {
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.sendKeys(firstName);
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        lastNameField.sendKeys(lastName);
        WebElement emailAddressField = driver.findElement(By.id("userEmail"));
        emailAddressField.sendKeys(email);
        WebElement ageField = driver.findElement(By.id("age"));
        ageField.sendKeys(age);
        WebElement salaryField = driver.findElement(By.id("salary"));
        salaryField.sendKeys(salary);
        WebElement departmentField = driver.findElement(By.id("department"));
        departmentField.sendKeys(department);
        WebElement submitButtonField = driver.findElement(By.id("submit"));
        submitButtonField.click();
    }

    //facem o metoda care sa valideze ca am adaugat o intrare noua in tabel si sa verifice valorile pe care le-am dat;
    public void validateThatNewRecordsAreAddedProperly() {
        List<WebElement> tableRowList = driver.findElements(By.xpath("//div[@class='rt-tbody']//div[@class='rt-tr -odd' or @class='rt-tr -even']"));
        Assert.assertTrue(tableRowList.size() > initialTableSize, "There are no new entries in the table!, initial table size: " +
                initialTableSize + " is the same with actual table size: " + tableRowList.size());
        String actualTableValues = tableRowList.get(tableRowList.size()-1).getText();
        System.out.println("New record values are: " + actualTableValues);
        Assert.assertTrue(actualTableValues.contains(firstName),"First name value is not correct. Expected first Name: " + firstName);
        Assert.assertTrue(actualTableValues.contains(lastName),"Last name value is not correct. Expected last Name: " + lastName);
        Assert.assertTrue(actualTableValues.contains(email),"Email value is not correct. Expected email: " + email);
        Assert.assertTrue(actualTableValues.contains(age),"Age value is not correct. Expected age: " + age);
        Assert.assertTrue(actualTableValues.contains(salary),"Salary value is not correct. Expected salary: " + salary);
        Assert.assertTrue(actualTableValues.contains(department),"Department value is not correct. Expected department: " + department);
    }
}
