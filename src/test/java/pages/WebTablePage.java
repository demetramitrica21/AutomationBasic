package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class WebTablePage extends BasePage {
    //locatori specifici;
    private By pageTitle = By.xpath("//h1[@class]");
    private By tableRowList = By.xpath("//div[@class='rt-tbody']//div[@class='rt-tr -odd' or @class='rt-tr -even']");
    private By addNewRecordButton = By.id("addNewRecordButton");
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailAddressField = By.id("userEmail");
    private By ageField = By.id("age");
    private By salaryField = By.id("salary");
    private By departmentField = By.id("department");
    private By submitButtonField = By.id("submit");


    public int initialTableSize = 0;

    public WebTablePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "Web Tables", "Page is not loaded properly");
    }

    public void webTablePageFlow(Map<String,Object> webTableEntryData){
        getTableSize();
        clickToAddNewRecord();
        fillFormValues(webTableEntryData);
        validateThatNewRecordsAreAddedProperly(webTableEntryData);
    }

    public int getTableSize() {
        initialTableSize = elementMethods.getElements(tableRowList).size();
        System.out.println("Numarul initial de randuri in tabel este: " + initialTableSize);
        return initialTableSize;
    }

    //facem o metoda noua care adauga rand nou in tabel, dand click pe ADD;

    public void clickToAddNewRecord() {
        elementMethods.clickElement(addNewRecordButton);
    }

    //facem o metoda care sa completeze toate campurile din formular;

    public void fillFormValues(Map<String,Object> webTableEntryData) {
        elementMethods.fillElement(firstNameField, (String) webTableEntryData.get("firstName"));
        elementMethods.fillElement(lastNameField, (String) webTableEntryData.get("lastName"));
        elementMethods.fillElement(emailAddressField, (String) webTableEntryData.get("email"));
        elementMethods.fillElement(ageField, (String) webTableEntryData.get("age"));
        elementMethods.fillElement(salaryField, (String) webTableEntryData.get("salary"));
        elementMethods.fillElement(departmentField, (String) webTableEntryData.get("department"));
        elementMethods.clickElement(submitButtonField);
    }

    //facem o metoda care sa valideze ca am adaugat o intrare noua in tabel si sa verifice valorile pe care le-am dat;
    public void validateThatNewRecordsAreAddedProperly(Map<String,Object> webTableEntryData ) {
        Assert.assertTrue(elementMethods.getElements(tableRowList).size() > initialTableSize, "There are no new entries in the table!, initial table size: " +
                initialTableSize + " is the same with actual table size: " + elementMethods.getElements(tableRowList).size());
        String actualTableValues = elementMethods.getElements(tableRowList).get(elementMethods.getElements(tableRowList).size() - 1).getText();
        System.out.println("New record values are: " + actualTableValues);
        Assert.assertTrue(actualTableValues.contains((String) webTableEntryData.get("firstName")),
                "First name value is not correct. Expected first Name: " + webTableEntryData.get("firstName"));
        Assert.assertTrue(actualTableValues.contains((String) webTableEntryData.get("lastName")),
                "Last name value is not correct. Expected last Name: " + webTableEntryData.get("lastName"));
        Assert.assertTrue(actualTableValues.contains((String) webTableEntryData.get("email")),
                "Email value is not correct. Expected email: " + webTableEntryData.get("email"));
        Assert.assertTrue(actualTableValues.contains((String) webTableEntryData.get("age")),
                "Age value is not correct. Expected age: " + webTableEntryData.get("age"));
        Assert.assertTrue(actualTableValues.contains((String) webTableEntryData.get("salary")),
                "Salary value is not correct. Expected salary: " + webTableEntryData.get("salary"));
        Assert.assertTrue(actualTableValues.contains((String) webTableEntryData.get("department")),
                "Department value is not correct. Expected department: " + webTableEntryData.get("department"));
    }
}
