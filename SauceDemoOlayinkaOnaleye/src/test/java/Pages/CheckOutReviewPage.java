package Pages;

import ActionDriver.Action;
import BaseClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutReviewPage extends BaseClass {

    Action action = new Action();
    private WebDriverWait wait;


    @FindBy(xpath = "//*[@id=\"finish\"]")
    private WebElement finishBtn;

    @FindBy(xpath = "//*[@id=\"checkout_complete_container\"]/h2")
    private WebElement successMsg;
    public CheckOutReviewPage(){
        PageFactory.initElements(getDriver(), this);
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    public void clickFinishBtn(){
        action.click(getDriver(),finishBtn);
    }

    public String getSuccessMsg(){
      String SuccessMsg=  successMsg.getText();
      return SuccessMsg;
    }

}
