import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PracticeFormTest {

    WebDriver driver;

    @Test
    public void practiceFormTest() {

        driver = new ChromeDriver();
        // navigam catre pagine website-ului
        driver.get("https://demoqa.com/");
        //facem fereastra browser-ului maximize
        driver.manage().window().maximize();
        //identificam meniul dorit si facem click pe el;
        WebElement FormMenu = driver.findElement(By.xpath("//h5[text()='Forms']"));
        //actionam butonul pe meniul de mai sus;
        // facem scroll pana in dreptul elementului pe care vrem sa actionam;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", FormMenu);
        FormMenu.click();
        //identificam submeniul dorit si facem click pe el;
        WebElement PracticeFormSubMenu = driver.findElement(By.xpath("//span[text()='Practice Form']"));
        PracticeFormSubMenu.click();
        //identificam elementele din formular si facem actiuni corespunzatoare pe fiecare;
        //completam field-ul de first name;
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        String firstNameText = "Mario";
        firstNameField.sendKeys(firstNameText);
        //completam field-ul de last name;
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        String lastNameText = "Luigi";
        lastNameField.sendKeys(lastNameText);
        //completam field-ul de email
        WebElement emailField = driver.findElement(By.id("userEmail"));
        String emailFieldText = "test@gmail.com";
        emailField.sendKeys(emailFieldText);
        js.executeScript("window.scrollBy(0,500)");
        //selectam gender
        WebElement genderMale = driver.findElement(By.xpath("//label[@for='gender-radio-1']"));
        WebElement genderFemale = driver.findElement(By.xpath("//label[@for='gender-radio-2']"));
        WebElement genderOther = driver.findElement(By.xpath("//label[@for='gender-radio-3']"));
        String genderValueText = "Male";
        //cream o lista de web elemente din alea 3 de sus;
        List<WebElement> genderList = List.of(genderMale, genderFemale, genderOther); //un nou mod de a crea o lista;
        for (int i = 0; i < genderList.size(); i++) {
            if (genderList.get(i).getText().equals(genderValueText)) {
                genderList.get(i).click();
                break;
            }
        }
        //completam field-ul de mobile phone
        WebElement mobilePhoneField = driver.findElement(By.id("userNumber"));
        String mobilePhoneText = "0756950504";
        mobilePhoneField.sendKeys(mobilePhoneText);
        //completam field-ul de date of birth;
        WebElement dateOfBirthInput = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirthInput.click();
        WebElement monthOfBirthElement = driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
        //declaram clasa
        Select selectMonth = new Select(monthOfBirthElement);
        String monthValueText = "April"; //cream o variabila locala;
        selectMonth.selectByVisibleText(monthValueText);
        WebElement yearOfBirthElement = driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
        //declaram clasa
        Select selectYear = new Select(yearOfBirthElement);
        String yearValueText = "2025";
        selectYear.selectByVisibleText(yearValueText);
        //alegem ziua nasterii dintr-o lista de zile posibile
        List<WebElement> dayOfBirthList = driver.findElements(By.xpath("//div[contains(@class,'react-datepicker__day')]"));
        String dayValueText = "14";
        for (int i = 0; i < dayOfBirthList.size(); i++) {
            if (dayOfBirthList.get(i).getText().equals(dayValueText)) {
                dayOfBirthList.get(i).click();
                break;
            }
        }
        //completam subject;
        WebElement subjectInputElement = driver.findElement(By.id("subjectsInput"));
        String mathSubjectText = "Maths";
        subjectInputElement.sendKeys(mathSubjectText);
        subjectInputElement.sendKeys(Keys.ENTER);
        String physicsSubjectText = "Physics";
        subjectInputElement.sendKeys(physicsSubjectText);
        subjectInputElement.sendKeys(Keys.ENTER);
        js.executeScript("window.scrollBy(0,200)"); //200 e pixel ca sa se duca in jos;
        //selectam check box-urile de hobbies;
        WebElement sportHobbyElement = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-1']"));
        WebElement readHobbyElement = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-2']"));
        WebElement musicHobbyElement = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-3']"));
        List<WebElement> hobbiesList = List.of(sportHobbyElement, readHobbyElement, musicHobbyElement); //am creat lista cu elementele dorite;
        String sportValueText = "Sports";
        String readValueText = "Reading";
        String musicValueText = "Music";
        List<String> hobbyValueTextList = List.of(sportValueText, readValueText, musicValueText);
        for (String hobby : hobbyValueTextList) {
            for (int i = 0; i < hobbiesList.size(); i++) {
                if (hobbiesList.get(i).getText().equals(hobby)) {
                    hobbiesList.get(i).click();
                }
            }
        }
        //facem scroll down;
        js.executeScript("window.scrollBy(0,200)");
        //Incarc o poza, un fisier pe care l-am download-at si am dat un filepath
        WebElement uploadFileElement = driver.findElement(By.id("uploadPicture"));
        String pictureFileText = "TestImage.jpg";
        String pictureFilePaths = "src/test/resources/picture/" + pictureFileText;
        File file = new File(pictureFilePaths);
        uploadFileElement.sendKeys(file.getAbsolutePath());
        //Identific elementul "addressField" de pe pagina web, dau click pe el si introduc textul"Adresa Testare 1";
        WebElement addressField = driver.findElement(By.id("currentAddress"));
        String addressValueText = "Strada Sigismund Toduta, nr. 1, ap. 5";
        addressField.sendKeys(addressValueText);
        //Aleg tara- state din lista disponibila, prin tastarea textului "NCR" si apoi apas tasta "Enter";
        WebElement stateInputElement = driver.findElement(By.id("react-select-3-input"));
        String stateValueText = "NCR"; //cream variabila locala;
        stateInputElement.sendKeys(stateValueText);
        stateInputElement.sendKeys(Keys.ENTER);
        //alegem orasul din lista disponibila prin tastarea textului "Delhi" si apoi apas tasta "Enter";
        WebElement cityInputElement = driver.findElement(By.id("react-select-4-input"));
        String cityValueText = "Delhi"; //cream variabila locala;
        cityInputElement.sendKeys(cityValueText);
        cityInputElement.sendKeys(Keys.ENTER);
        //apasam butonul submit;
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        //validam tabelul cu datele de intrare folosite;

        //facem un Hash Map cu expected values;
        HashMap<String, String> expectedValues = new HashMap<>();
        expectedValues.put("Student Name", firstNameText + " " + lastNameText);
        expectedValues.put("Student Email", emailFieldText);
        expectedValues.put("Gender", genderValueText);
        expectedValues.put("Mobile", mobilePhoneText);
        expectedValues.put("Date of Birth", dayValueText + " " + monthValueText + "," + yearValueText);
        expectedValues.put("Subjects", mathSubjectText + ", " + physicsSubjectText);
        expectedValues.put("Hobbies", sportValueText + ", " + readValueText + ", " + musicValueText);
        expectedValues.put("Picture", pictureFileText);
        expectedValues.put("Address", addressValueText);
        expectedValues.put("State and City", stateValueText + " " + cityValueText);

        //declaram listele cu valorile actuale din tabel;
        List<WebElement> submitTableKeys = driver.findElements(By.xpath("//tbody//td[1]"));
        List<WebElement> submitTableValues = driver.findElements(By.xpath("//tbody//td[2]"));
        HashMap<String, String> actualValues = new HashMap<>();
        //indiferetn ca alegem submitTablekeys sau values, parcurge oricum ambele liste;
        for (int i = 0; i < submitTableKeys.size(); i++) {
            actualValues.put(submitTableKeys.get(i).getText(), submitTableValues.get(i).getText()); //parcurgem fiecare valoare/cheie si luam textul de pe ea si o adagam in HashMap;
        }

        //assert-ul este validarea ca un anumit obiect este egal cu altul sau ca valorile unui obiect sunt egale cu ale altuia;
        //sau ca un element exista;

        Assert.assertEquals(actualValues, expectedValues, "Actual Values: " + actualValues + "are not equal/are not the same with the expected values: " + expectedValues);

    }
}
