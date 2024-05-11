package TestCases;
import BaseClass.BaseClass;
import Pages.InventoryPage;
import Pages.LoginPage;
import Utilities.Log;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseClass {
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Parameters("browser")
    @BeforeMethod(groups={"Smoke","Sanity","Regression"})

    public void setUp(String browser){

        launchApp(browser);
    }

      @Test
      public void TC_001_Validate_That_User_Shall_Be_Able_To_Launch_Url(){
          Log.startTestCase("URL Launch Test");
          loginPage = new LoginPage();
          Log.info("System Launches URL");
          String loginUrl= loginPage.getLoginPageUrl();
          Log.info("Verifying if User has successfully launched the URL");
          boolean loginUrlContains =  loginUrl.contains(prop.getProperty("testUrl"));
          Assert.assertTrue(loginUrlContains);
          Log.info("Launching of URL is Successful");
          Log.endTestCase("loginTest");
      }

   @Test(groups="Smoke")
    public void TC_002_Validate_User_Cannot_Login_With_Empty_Username_And_Empty_Password() throws InterruptedException {
        Log.startTestCase("Empty User and Password Test");
        loginPage = new LoginPage();
        Log.info("System Launches URL");
        loginPage.invalidLoginAttempt(prop.getProperty("emptyUsername"),prop.getProperty("emptyPassword"));
        Log.info("Verifying if Login Error Message is displayed on the page");
        Assert.assertEquals(prop.getProperty("error.emptyCredentials"),loginPage.getErrorText() );
        Log.info("Login Error verified successfully");

    }

    @Test(groups="Smoke")
    public void TC_003_Validate_User_Cannot_Login_With_inValid_Username_And_Password() throws InterruptedException {
        Log.startTestCase("Invalid User and Password Test");
        loginPage = new LoginPage();
        Log.info("System Launches URL");
        loginPage.invalidLoginAttempt(prop.getProperty("invalidUsername"),prop.getProperty("invalidPassword"));
        Log.info("Verifying if Login Error Message is displayed on the page");
        Assert.assertEquals(prop.getProperty("error.invalidCredentials"),loginPage.getErrorText());
        Log.info("Login Error verified successfully");
    }


   @Test(groups="Regression")
    public void TC_004_Validate_User_Can_Login_With_Valid_Credentials() throws InterruptedException {
       Log.startTestCase("Successful Login Test");
        loginPage = new LoginPage();
       Log.info("System Launches URL");
        inventoryPage= loginPage.login(prop.getProperty("validUsername"),prop.getProperty("validPassword"));
       Log.info("Verifying if Login Error Message is displayed on the page");
        Assert.assertTrue(getDriver().getCurrentUrl().contains(prop.getProperty("testUrl")));
       Log.info("Logged Successfully");

    }

    @AfterMethod(groups={"Smoke","Sanity","Regression"})
    public void tearDown(){
        exitApp();
    }


}
