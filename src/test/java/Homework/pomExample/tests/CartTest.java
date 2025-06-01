package Homework.pomExample.tests;

import Homework.pomExample.pages.CartPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void addItemToCart(){
        CartPage cartPage= new CartPage(driver);
        cartPage.isPageLoaded();
        cartPage.acceptCookiesPolicy();
        cartPage.addItemToCart("rochii");
    }
}
