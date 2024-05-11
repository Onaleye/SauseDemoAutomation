package Pages;

import ActionDriver.Action;
import BaseClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BaseClass {

    Action action = new Action();
    private WebDriverWait wait;

    @FindBy(className = "checkout_button")
    private WebElement checkoutButton;


    public CartPage(){
        PageFactory.initElements(getDriver(), this);
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    public boolean checkOut() {
        boolean checkoutBtnStatus =  action.isDisplayed(getDriver(),checkoutButton);
        return checkoutBtnStatus;
    }
}
