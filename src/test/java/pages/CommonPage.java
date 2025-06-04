package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CommonPage extends BasePage {
    //locatori specifici paginii;
    private By subMenuListLocator = By.xpath("//span[@class='text']");

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        Assert.assertEquals(driver.getTitle(), "DEMOQA", "Page is not loaded properly");
    }

    public void goToDesiredSubMenu(String subMenuValue) {
        elementMethods.scrollPageDown("500");
        elementMethods.chooseElementFromListByText(subMenuListLocator, subMenuValue);
    }
}
