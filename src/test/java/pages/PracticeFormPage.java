package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PracticeFormPage extends BasePage {
    //locatori specifici;
    private By pageTitle = By.xpath("//h1[@class]");
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By genderMale = By.xpath("//label[@for='gender-radio-1']");
    private By genderFemale = By.xpath("//label[@for='gender-radio-2']");
    private By genderOther = By.xpath("//label[@for='gender-radio-3']");
    private By mobilePhoneField = By.id("userNumber");
    private By dateOfBirthInput = By.id("dateOfBirthInput");
    private By monthOfBirthLocator = By.xpath("//select[@class='react-datepicker__month-select']");
    private By yearOfBirthLocator = By.xpath("//select[@class='react-datepicker__year-select']");
    private By dayOfBirthListLocator = By.xpath("//div[contains(@class,'react-datepicker__day')]");
    private By subjectInputElement = By.id("subjectsInput");
    private By sportHobbyElement = By.xpath("//label[@for='hobbies-checkbox-1']");
    private By readHobbyElement = By.xpath("//label[@for='hobbies-checkbox-2']");
    private By musicHobbyElement = By.xpath("//label[@for='hobbies-checkbox-3']");
    private By uploadFileElement = By.id("uploadPicture");
    private By addressField = By.id("currentAddress");
    private By stateInputElement = By.id("react-select-3-input");
    private By cityInputElement= By.id("react-select-4-input");
    private By submitButton=By.id("submit");
    private By submitTableKeysLocator = By.xpath("//tbody//td[1]");
    private By submitTableValuesLocator = By.xpath("//tbody//td[2]");
    private By hobbyElementLocator = By.xpath("//input[@type='checkbox' and @class='custom-control-input']");

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "Practice Form", "Page is not loaded properly");
    }

    public void fillEntireForm(Map<String,Object> practiceFormData) {
        fillFirstName((String) practiceFormData.get("firstNameText"));
        fillLastName((String) practiceFormData.get("lastNameText"));
        fillEmail((String) practiceFormData.get("emailFieldText"));
        elementMethods.scrollPageDown("500");
        chooseGender((String) practiceFormData.get("genderValueText"));
        fillPhoneNumber((String) practiceFormData.get("mobilePhoneText"));
        fillDateOfBirth((String) practiceFormData.get("monthValueText"), (String) practiceFormData.get("yearValueText"),
                (String) practiceFormData.get("dayValueText"));
        chooseSubjects((String) practiceFormData.get("mathSubjectText"), (String) practiceFormData.get("physicsSubjectText"));
        elementMethods.scrollPageDown("200");
        chooseHobbies((String) practiceFormData.get("sportValueText"), (String) practiceFormData.get("readValueText"),
                (String) practiceFormData.get("musicValueText"));
        elementMethods.scrollPageDown("200");
        uploadPicture((String) practiceFormData.get("pictureFileText"));
        fillAddress((String) practiceFormData.get("addressValueText"));
        fillStateAndCity((String) practiceFormData.get("stateValueText"), (String) practiceFormData.get("cityValueText"));
        clickOnSubmitButton();
        getExpectedValues(practiceFormData);
        getActualValues();
    }

    public void fillFirstName(String firstNameValue) {
        elementMethods.fillElement(firstNameField,firstNameValue);
    }

    public void fillLastName(String lastNameValue) {
        elementMethods.fillElement(lastNameField,lastNameValue);
    }

    public void fillEmail(String emailValue) {
        elementMethods.fillElement(emailField,emailValue);
    }

    public void chooseGender(String genderValue) {
        List<WebElement> genderList = List.of(driver.findElement(genderMale),
                driver.findElement(genderFemale), driver.findElement(genderOther));
        elementMethods.chooseElementFromListByText(genderList,genderValue);
    }

    public void fillPhoneNumber(String mobilePhoneValue) {
        elementMethods.fillElement(mobilePhoneField,mobilePhoneValue);
    }

    public void fillDateOfBirth(String monthValue, String yearValue, String dayValue) {
        elementMethods.clickElement(dateOfBirthInput);
        elementMethods.selectElementByText(monthOfBirthLocator,monthValue);
        elementMethods.selectElementByText(yearOfBirthLocator,yearValue);
        elementMethods.chooseElementFromListByText(dayOfBirthListLocator,dayValue);
    }

    public void chooseSubjects(String mathSubjectValue, String physicsSubjectValue) {
        elementMethods.fillElement(subjectInputElement,mathSubjectValue);
        elementMethods.fillElement(subjectInputElement,Keys.ENTER);
        elementMethods.fillElement(subjectInputElement,physicsSubjectValue);
        elementMethods.fillElement(subjectInputElement,Keys.ENTER);
    }

    public void chooseHobbies(String sportValue, String readValue, String musicValue) {
        List<WebElement> hobbiesList = List.of(driver.findElement(sportHobbyElement),
                driver.findElement(readHobbyElement), driver.findElement(musicHobbyElement));
        List<String> hobbyValueTextList = List.of(sportValue,readValue,musicValue);
        for(String hobby: hobbyValueTextList){
            elementMethods.chooseElementFromListByText(hobbiesList,hobby);
        }
    }

    public void uploadPicture(String pictureFileValue) {
        elementMethods.uploadDocument(uploadFileElement,pictureFileValue);
    }

    public void fillAddress(String addressValue) {
        elementMethods.fillElement(addressField,addressValue);
    }

    public void fillStateAndCity(String stateValue, String cityValue) {
        elementMethods.fillElement(stateInputElement,stateValue);
        elementMethods.fillElement(stateInputElement,Keys.ENTER);
        elementMethods.fillElement(cityInputElement,cityValue);
        elementMethods.fillElement(cityInputElement,Keys.ENTER);
    }

    public void clickOnSubmitButton(){
        elementMethods.clickElement(submitButton);
    }

    public HashMap<String, String> getExpectedValues(Map<String,Object> practiceFormData){
        HashMap<String,String> expectedValues= new HashMap<>();
        expectedValues.put("Student Name", practiceFormData.get("firstNameText") + " " + practiceFormData.get("lastNameText"));
        expectedValues.put("Student Email", (String) practiceFormData.get("emailFieldText"));
        expectedValues.put("Gender", (String) practiceFormData.get("genderValueText"));
        expectedValues.put("Mobile", (String) practiceFormData.get("mobilePhoneText"));
        expectedValues.put("Date of Birth", practiceFormData.get("dayValueText") + " " +
                practiceFormData.get("monthValueText") + "," + practiceFormData.get("yearValueText"));
        expectedValues.put("Subjects", practiceFormData.get("mathSubjectText") + ", " + practiceFormData.get("physicsSubjectText"));
        expectedValues.put("Hobbies", practiceFormData.get("sportValueText") + ", " + practiceFormData.get("readValueText") + ", " +
                practiceFormData.get("musicValueText"));
        expectedValues.put("Picture", (String) practiceFormData.get("pictureFileText"));
        expectedValues.put("Address", (String) practiceFormData.get("addressValueText"));
        expectedValues.put("State and City", practiceFormData.get("stateValueText") + " " + practiceFormData.get("cityValueText"));
        return expectedValues;
    }

    public HashMap<String,String> getActualValues(){
        HashMap<String,String> actualValues= new HashMap<>();
        //indiferent ca alegem submitTablekeys sau values, parcurge oricum ambele liste;
        for (int i = 0; i < elementMethods.getElements(submitTableKeysLocator).size(); i++) {
            actualValues.put(elementMethods.getElements(submitTableKeysLocator).get(i).getText(),
                    elementMethods.getElements(submitTableValuesLocator).get(i).getText());
            //parcurgem fiecare valoare/cheie si luam textul de pe ea si o adagam in HashMap;
        }
        return actualValues;
    }

    public void validateThatExpectedValuesEqualActualValues(Map<String, Object> practiceFormData){
        Assert.assertEquals(getActualValues(), getExpectedValues(practiceFormData), "Actual Values: " + getActualValues() +
                "are not equal/are not the same with the expected values: " + getExpectedValues(practiceFormData));
    }
}
