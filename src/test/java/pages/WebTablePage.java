package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

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
        logInfo(PASS_STEP,"Validate that WebTablePage is loaded properly");
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
        logInfo(INFO_STEP,"Get initial table size -> Initial table size is: " + initialTableSize);
        System.out.println("Numarul initial de randuri in tabel este: " + initialTableSize);
        return initialTableSize;
    }

    //facem o metoda noua care adauga rand nou in tabel, dand click pe ADD;

    public void clickToAddNewRecord() {
        logInfo(INFO_STEP,"User clicks on add new record button");
        elementMethods.clickElement(addNewRecordButton);
    }

    //facem o metoda care sa completeze toate campurile din formular;

    public void fillFormValues(Map<String,Object> webTableEntryData) {
        logInfo(INFO_STEP,"User fills form with valid values");
        elementMethods.fillElement(firstNameField, (String) webTableEntryData.get("firstName"));
        logInfo(INFO_STEP,"First name value: " + (String) webTableEntryData.get("firstName"));
        elementMethods.fillElement(lastNameField, (String) webTableEntryData.get("lastName"));
        logInfo(INFO_STEP,"Last name value: " + (String) webTableEntryData.get("lastName"));
        elementMethods.fillElement(emailAddressField, (String) webTableEntryData.get("email"));
        logInfo(INFO_STEP,"Email value: " + (String) webTableEntryData.get("email"));
        elementMethods.fillElement(ageField, (String) webTableEntryData.get("age"));
        logInfo(INFO_STEP,"Age value: " + (String) webTableEntryData.get("age"));
        elementMethods.fillElement(salaryField, (String) webTableEntryData.get("salary"));
        logInfo(INFO_STEP,"Salary value: " + (String) webTableEntryData.get("salary"));
        elementMethods.fillElement(departmentField, (String) webTableEntryData.get("department"));
        logInfo(INFO_STEP,"Department value: " + (String) webTableEntryData.get("department"));
        elementMethods.clickElement(submitButtonField);
        logInfo(INFO_STEP,"User clicks on submit button");
    }

    //facem o metoda care sa valideze ca am adaugat o intrare noua in tabel si sa verifice valorile pe care le-am dat;
    public void validateThatNewRecordsAreAddedProperly(Map<String,Object> webTableEntryData ) {
        logInfo(PASS_STEP,"Validate that a new entry is added to the table -> actual table size is: " + elementMethods.getElements(tableRowList).size());
        Assert.assertTrue(elementMethods.getElements(tableRowList).size() > initialTableSize, "There are no new entries in the table!, initial table size: " +
                initialTableSize + " is the same with actual table size: " + elementMethods.getElements(tableRowList).size());
        String actualTableValues = elementMethods.getElements(tableRowList).get(elementMethods.getElements(tableRowList).size() - 1).getText();
        System.out.println("New record values are: " + actualTableValues);
        logInfo(PASS_STEP,"Validate that input data are displayed properly in the table: " + actualTableValues);
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
