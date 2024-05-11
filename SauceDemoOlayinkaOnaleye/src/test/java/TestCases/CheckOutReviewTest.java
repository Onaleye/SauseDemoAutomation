package TestCases;

import BaseClass.BaseClass;
import Pages.CartPage;
import Pages.CheckOutReviewPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckOutReviewTest extends BaseClass {

    LoginPage loginPage;
    CartPage cartPage;
    InventoryPage inventoryPage;
    CheckoutTest checkoutTest;
    CheckOutReviewPage checkOutReviewPage;

    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})

    public void setUp(String browser){

        launchApp(browser);
    }

    @Test
    public void TC_013_Validate_That_System_Display_Confirm_Success_Message(){
        checkoutTest=new CheckoutTest();

        checkoutTest.submitCheckOutDetails();
        checkOutReviewPage=new CheckOutReviewPage();
        checkOutReviewPage.clickFinishBtn();

      String expectedSuccessMsg =checkOutReviewPage.getSuccessMsg();
      String ActualSuccessMsg= prop.getProperty("successMsg");
        Assert.assertEquals(ActualSuccessMsg,expectedSuccessMsg);

    }

    @AfterMethod(groups={"Smoke","Sanity","Regression"})
    public void tearDown(){
        exitApp();
    }
}
