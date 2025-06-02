package Homework.pomExample.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.BasePage;

import java.time.Duration;

public class CartPage extends BasePage {
    //locatori specifici;
    private By pageTitle = By.xpath("//span[@class='Heading u-h4']");
    private By acceptCookiesLocator = By.id("shopify-pc__banner__btn-accept");
    private By clickSearchIconLocator = By.xpath("//a[@href='/search']");
    private By typeProductInSearchBarLocator = By.xpath("//input[@type='search']");
    private By searchResultsLocator = By.xpath("//div[@class='Search__Results']");
    private By clickOnDesiredItemLocator = By.xpath("//div[@class='ProductItem ']");
    private By closePopUpLocator = By.xpath("//button[@aria-label='Inchide']");
    private By chooseDesiredSizeLocator = By.xpath("//label[text()='S']");
    private By addItemToCartLocator = By.xpath("//span[text()='Adauga in cos']");
    private By sideBarCartLocator = By.id("sidebar-cart");
    private By cartItemLocator= By.xpath("//div[@class='CartItem']");
    private By cartIconLocator = By.xpath("//a[@data-drawer-id='sidebar-cart']");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isPageLoaded() {
        Assert.assertEquals(driver.findElement(pageTitle).getText(), "ANASOFIA", "Page is not loaded properly");
    }

    public void acceptCookiesPolicy() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesLocator));
        if(acceptCookies.isDisplayed()) {
            acceptCookies.click();
        }
    }

    //Test Case 1: Add item to cart and refresh the page to see if keeps it still in the cart;
    public void addItemToCart(String productName) {
        driver.findElement(clickSearchIconLocator).click();
        driver.findElement(typeProductInSearchBarLocator).sendKeys(productName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Wait until element is visible, not just present
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(searchResultsLocator)));
        scrollIntoElement(driver.findElement(searchResultsLocator));
        driver.findElement(clickOnDesiredItemLocator).click();
        closePopUpBox();
        driver.findElement(chooseDesiredSizeLocator).click();
        driver.findElement(addItemToCartLocator).click();
        WebDriverWait waitForTheSideBar = new WebDriverWait(driver, Duration.ofSeconds(7));
        waitForTheSideBar.until(ExpectedConditions.visibilityOf(driver.findElement(sideBarCartLocator)));
        WebDriverWait waitForTheItemCart = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForTheItemCart.until(ExpectedConditions.visibilityOf(driver.findElement(cartItemLocator)));
        Assert.assertTrue(driver.findElement(cartItemLocator).isDisplayed(),"The item was not added to the cart properly");
        //refresh the page
        driver.navigate().refresh();
        //go to cart again and check if the item is still there after refreshing the page;
        driver.findElement(cartIconLocator).click();
        WebDriverWait waitForTheSideBarAgain = new WebDriverWait(driver, Duration.ofSeconds(7));
        waitForTheSideBarAgain.until(ExpectedConditions.visibilityOf(driver.findElement(sideBarCartLocator)));
        WebDriverWait waitToCheckTheCart = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitToCheckTheCart.until(ExpectedConditions.visibilityOf(driver.findElement(cartItemLocator)));
        Assert.assertTrue(driver.findElement(cartItemLocator).isDisplayed(),"The item is not there after refresh");
    }

    public void closePopUpBox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement closePopUpBox = wait.until(ExpectedConditions.elementToBeClickable(closePopUpLocator));
        if(closePopUpBox.isDisplayed()) {
            closePopUpBox.click();
        }
    }

    public void scrollIntoElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
