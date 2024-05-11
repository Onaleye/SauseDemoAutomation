package TestCases;

import BaseClass.BaseClass;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LogoutTest extends BaseClass {

    LoginPage loginPage;
    CartPage cartPage;
    InventoryPage inventoryPage;
    CheckoutTest checkoutTest;
    CheckOutReviewPage checkOutReviewPage;
    LogoutPage logoutPage;

    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})

    public void setUp(String browser){

        launchApp(browser);
    }

    @Test
    public void confirmSuccessMessage(){
        checkoutTest=new CheckoutTest();

        checkoutTest.submitCheckOutDetails();
        checkOutReviewPage=new CheckOutReviewPage();
        checkOutReviewPage.clickFinishBtn();

        String expectedSuccessMsg =checkOutReviewPage.getSuccessMsg();
        String ActualSuccessMsg= prop.getProperty("successMsg");
        Assert.assertEquals(ActualSuccessMsg,expectedSuccessMsg);


    }

    @Test
    public  void TC_014_Validate_That_user_Can_Logout_Successfully() throws InterruptedException {
        checkoutTest=new CheckoutTest();

        checkoutTest.submitCheckOutDetails();
        checkOutReviewPage=new CheckOutReviewPage();
        checkOutReviewPage.clickFinishBtn();
        logoutPage= new LogoutPage();
        Thread.sleep(5);
        String ActualLogoutUrl = logoutPage.clickLogout();
        Thread.sleep(5);
        Assert.assertEquals(ActualLogoutUrl,prop.getProperty("url"));
    }

    @AfterMethod(groups={"Smoke","Sanity","Regression"})
    public void tearDown(){
        exitApp();
    }
}
