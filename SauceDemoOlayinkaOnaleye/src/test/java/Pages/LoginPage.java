package Pages;
import ActionDriver.Action;
import BaseClass.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BaseClass {

    Action action = new Action();
    private WebDriverWait wait;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath=  "//*[@id=\"login-button\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test=\"error\" and contains(text(), \"username and password do not match\")]\n")
    public WebElement invalidUserAndPasswordError;

    //@FindBy(xpath = "//h3[@data-test=\"error\" and contains(text(), \"username is required\")]\n")
    //public WebElement emptyUserNameError;
    @FindBy(css = "div.error-message-container h3[data-test='error']")
    public WebElement errorText;
    @FindBy(xpath = "//h3[@data-test=\"error\" and contains(text(), \"password is required\")]\n")
    public WebElement emptyPasswordError;

    public LoginPage(){
        PageFactory.initElements(getDriver(), this);
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    public String getLoginPageUrl(){
        String loginPageUrl=getDriver().getCurrentUrl();
        return loginPageUrl;
    }
    public String getUserName() {
        String theField = usernameField.getText();
        return theField;
    }

    public InventoryPage login(String theUsername, String thePassword) {
        action.type(usernameField,theUsername);
        action.type(passwordField,thePassword);
        action.click(getDriver(),loginButton);
        return new InventoryPage();
    }
    public void invalidLoginAttempt(String uName, String pWord){
        action.type(usernameField,uName);
        action.type(passwordField,pWord);
        action.click(getDriver(),loginButton);
    }


    public String getErrorText() {
        // Wait for the error message container to be visible
        WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(errorText));

        // Return the text of the error message
        return errorText.getText();
    }
    public String getInvalidError(){
        return invalidUserAndPasswordError.getText();
    }

}
