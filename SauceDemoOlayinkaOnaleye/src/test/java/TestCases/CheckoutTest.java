package TestCases;

import BaseClass.BaseClass;
import Pages.CartPage;
import Pages.CheckOutPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseClass {

    LoginPage loginPage;
    CartPage cartPage;
    InventoryPage inventoryPage;
    CheckOutPage checkOutPage;



    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})

    public void setUp(String browser){

        launchApp(browser);
    }

    @Test
    public void TC_012_Validate_That_User_CheckOutBtn() {
        loginPage = new LoginPage();
        inventoryPage = loginPage.login(prop.getProperty("validUsername"), prop.getProperty("validPassword"));
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.addItemToCart("Sauce Labs Bolt T-Shirt");
        cartPage = inventoryPage.navigateToCartPage();
        Assert.assertTrue(cartPage.checkOut());
        checkOutPage = new CheckOutPage();
        checkOutPage.clickToCheckOut();
    }

    @Test
    public void submitCheckOutDetails(){

        TC_012_Validate_That_User_CheckOutBtn();
        checkOutPage.submitAddressDetails(prop.getProperty("lastName"), prop.getProperty("firstName"), prop.getProperty("zipCode") );

    }

    @AfterMethod(groups={"Smoke","Sanity","Regression"})
    public void tearDown(){
        exitApp();
    }

}
