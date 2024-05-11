package Pages;

import ActionDriver.Action;
import BaseClass.BaseClass;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckOutPage extends BaseClass {

    Action action = new Action();
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"checkout\"]")
    private WebElement checkoutButton;

    @FindBy(xpath = "//*[@id=\"first-name\"]")
    private WebElement firstNameText;

    @FindBy(xpath = "//*[@id=\"last-name\"]")
    private WebElement lastNameText;

    @FindBy(xpath = "//*[@id=\"postal-code\"]")
    private WebElement zipCodeText;

    @FindBy(xpath = "//*[@id=\"continue\"]")
    private WebElement clickContinueBtn;

    public CheckOutPage(){
        PageFactory.initElements(getDriver(), this);
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }


public void clickToCheckOut(){
        action.click(getDriver(),checkoutButton);
    }

    public CheckOutReviewPage submitAddressDetails(String fName, String lName, String zCode){
        action.type(firstNameText,fName);
        action.type(lastNameText, lName);
        action.type(zipCodeText,zCode);
        action.click(getDriver(), clickContinueBtn);
        return new CheckOutReviewPage();
    }
}
