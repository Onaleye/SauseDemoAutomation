package Pages;

import ActionDriver.Action;
import BaseClass.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage extends BaseClass {

    Action action = new Action();
    private WebDriverWait wait;
    @FindBy(xpath = "//*[@id=\"continue\"]")
    private WebElement clickContinueBtn;

    @FindBy(xpath = "//*[@id=\"react-burger-menu-btn\"]")
    private WebElement menuBar;

    @FindBy(xpath = "//*[@id=\"logout_sidebar_link\"]")
    private WebElement logoutBtn;

    public LogoutPage(){
        PageFactory.initElements(getDriver(), this);
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    public String clickLogout() throws InterruptedException {
        action.click(getDriver(), menuBar);
        Thread.sleep(5);
        action.click(getDriver(),logoutBtn);
        String logoutUrl=getDriver().getCurrentUrl();
        return logoutUrl;

    }
}
