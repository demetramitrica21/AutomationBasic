package tests;

import org.testng.annotations.Test;
import pages.CommonPage;
import pages.HomePage;
import pages.WebTablePage;
import propertyUtility.PropertyUtility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.MenuConstants.ELEMENTS_MENU;
import static constants.SubMenuConstants.WEB_TABLES_SUBMENU;

public class WebTableMultipleInputsTest extends BaseTest {

    @Test
    public void webTableTest() {
        HomePage homePage = new HomePage(driver);
        homePage.isPageLoaded();
        homePage.goToDesiredMenu(ELEMENTS_MENU);
        CommonPage commonPage = new CommonPage(driver);
        commonPage.isPageLoaded();
        commonPage.goToDesiredSubMenu(WEB_TABLES_SUBMENU);
        WebTablePage webTablePage = new WebTablePage(driver);
        webTablePage.isPageLoaded();
        propertyUtility = new PropertyUtility("WebTableMultipleInputsTest");
        List<String> firstNameValues = propertyUtility.getPropertiesAsList("firstName");
        List<String> lastNameValues = propertyUtility.getPropertiesAsList("lastName");
        List<String> emailValues = propertyUtility.getPropertiesAsList("email");
        List<String> ageValues = propertyUtility.getPropertiesAsList("age");
        List<String> salaryValues = propertyUtility.getPropertiesAsList("salary");
        List<String> departmentValues = propertyUtility.getPropertiesAsList("department");
        for(int i=0; i< firstNameValues.size();i++){
            Map<String,Object> webTableEntries = new HashMap<>();
            webTableEntries.put("firstName",firstNameValues.get(i));
            webTableEntries.put("lastName",lastNameValues.get(i));
            webTableEntries.put("email",emailValues.get(i));
            webTableEntries.put("age",ageValues.get(i));
            webTableEntries.put("salary",salaryValues.get(i));
            webTableEntries.put("department",departmentValues.get(i));
            webTablePage.webTablePageFlow(webTableEntries);
        }

        propertyUtility = new PropertyUtility("WebTableTest");
        Map<String,Object> webTableSingleEntries = propertyUtility.getAllProperties();
        webTablePage.webTablePageFlow(webTableSingleEntries);
    }
}
