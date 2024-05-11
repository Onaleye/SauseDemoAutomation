package TestCases;

import BaseClass.BaseClass;
import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import Utilities.Log;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryTest extends BaseClass {

    LoginPage loginPage;
    CartPage cartPage;
    InventoryPage inventoryPage;

    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})

    public void setUp(String browser){

        launchApp(browser);
    }

       @Test
        public void TC_005_Validate_That_User_Can_View_Inventory_List() throws IOException {
            loginPage = new LoginPage();
            inventoryPage= loginPage.login(prop.getProperty("validUsername"),prop.getProperty("validPassword"));
            Assert.assertTrue(inventoryPage.isInventoryListDisplayed());
        }

       @Test
        public void TC_006_Validate_That_User_can_Sort_Inventory_By_Price_Low_To_High() throws IOException, InterruptedException {
            loginPage = new LoginPage();
            inventoryPage= loginPage.login(prop.getProperty("validUsername"),prop.getProperty("validPassword"));
            inventoryPage.sortInventoryByPriceLowToHigh();

            // Get prices of all items
            List<WebElement> itemPrices = inventoryPage.getInventoryPrice();
            List<Double> prices = new ArrayList<>();
            for (WebElement priceElement : itemPrices) {
                String priceString = priceElement.getText().replace("$", "");
                prices.add(Double.parseDouble(priceString));
            }
            // Verify sorting
            boolean sorted = true;
            for (int i = 1; i < prices.size(); i++) {
                if (prices.get(i) < prices.get(i - 1)) {
                    sorted = false;
                    break;
                }
            }

            Assert.assertTrue(sorted,"Inventory items are not sorted by price (low to high)");
        }

         @Test
         public void TC_007_Validate_That_User_Can_Sort_Inventory_By_Price_High_To_Low() throws IOException {
            loginPage = new LoginPage();
            inventoryPage= loginPage.login(prop.getProperty("validUsername"),prop.getProperty("validPassword"));
            inventoryPage.sortInventoryByPriceHighToLow();

            // Get prices of all items
            List<WebElement> itemPrices = inventoryPage.getInventoryPrice();
            List<Double> prices = new ArrayList<>();
            for (WebElement priceElement : itemPrices) {
                String priceString = priceElement.getText().replace("$", "");
                prices.add(Double.parseDouble(priceString));
            }

            // Verify sorting
            boolean sorted = true;
            for (int i = 1; i < prices.size(); i++) {
                if (prices.get(i) > prices.get(i - 1)) {
                    sorted = false;
                    break;
                }
            }
            Assert.assertTrue( sorted, "Inventory items are not sorted by price (high to low)");
        }

    @Test
    public void TC_008_Validate_User_Can_Add_Items_To_Cart() throws IOException {
        loginPage = new LoginPage();
        inventoryPage= loginPage.login(prop.getProperty("validUsername"),prop.getProperty("validPassword"));

        cartPage = new CartPage();

        // Add items to cart
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.addItemToCart("Sauce Labs Bolt T-Shirt");

        // Validate cart count
        int expectedItemCount = 2; // Update based on the number of items added
        int actualItemCount = inventoryPage.getItemCount();
        assert actualItemCount == expectedItemCount : "Incorrect number of items in cart";
    }

@Test
public  void TC_009_Validate_That_User_Can_Remove_Item()throws IOException{
    loginPage = new LoginPage();
    inventoryPage= loginPage.login(prop.getProperty("validUsername"),prop.getProperty("validPassword"));
    inventoryPage.addItemToCart("Sauce Labs Backpack");
    inventoryPage.addItemToCart("Sauce Labs Bolt T-Shirt");
    Assert.assertTrue(inventoryPage.removeItems());
}

@Test
public void TC_010_Validate_That_User_Can_navigate_To_CartPage(){
    loginPage = new LoginPage();
    inventoryPage= loginPage.login(prop.getProperty("validUsername"),prop.getProperty("validPassword"));
    inventoryPage.addItemToCart("Sauce Labs Backpack");
    inventoryPage.addItemToCart("Sauce Labs Bolt T-Shirt");
    cartPage = inventoryPage.navigateToCartPage();
}


    @AfterMethod(groups={"Smoke","Sanity","Regression"})
    public void tearDown(){
        exitApp();
    }


}


