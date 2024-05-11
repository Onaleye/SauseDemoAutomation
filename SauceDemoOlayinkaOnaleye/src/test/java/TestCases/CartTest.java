package TestCases;

import BaseClass.BaseClass;
import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CartTest extends BaseClass {

    LoginPage loginPage;
    CartPage cartPage;
    InventoryPage inventoryPage;

    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})

    public void setUp(String browser){

        launchApp(browser);
    }
    @Test
    public void TC_011_Validate_That_user_CheckOutBtn(){
        loginPage = new LoginPage();
        inventoryPage= loginPage.login(prop.getProperty("validUsername"),prop.getProperty("validPassword"));
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.addItemToCart("Sauce Labs Bolt T-Shirt");
        cartPage = inventoryPage.navigateToCartPage();


    }
}
