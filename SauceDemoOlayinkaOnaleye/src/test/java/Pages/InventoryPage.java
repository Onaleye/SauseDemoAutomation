package Pages;
import ActionDriver.Action;
import BaseClass.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage extends BaseClass {

    Action action = new Action();
    private WebDriverWait wait;

    @FindBy(className = "inventory_list")
    private WebElement inventory;
    @FindBy(className = "inventory_container")
    private WebElement inventoryContainer;

    @FindBy(className = "product_sort_container")
    private WebElement sortingDropdown;

    @FindBy(xpath = "//option[text()='Price (low to high)']")
    private WebElement priceLowToHighOption;

    @FindBy(xpath = "//option[text()='Price (high to low)']")
    private WebElement priceHighToLowOption;

    @FindBy(className = "product_sort_container")
    private WebElement productContainer;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> inventoryPrice;
    @FindBy(xpath = "//*[@id=\"add-to-cart-sauce-labs-backpack\"]")
    private WebElement clickBackPack;

    @FindBy(xpath = "//*[@id=\"add-to-cart-sauce-labs-bike-light\"]")
    private WebElement clickBikeLight;

    @FindBy(xpath = "//*[@id=\"add-to-cart-sauce-labs-onesie\"]")
    private WebElement clickLabsOnesie;

    @FindBy(xpath = "//*[@id=\"shopping_cart_container\"]/a/span")
    private WebElement itemCount;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(xpath = "//*[@id=\"remove-sauce-labs-backpack\"]")
    private  WebElement removeItem;

    @FindBy(xpath ="//*[@id=\"shopping_cart_container\"]/a")
    private WebElement clickCartToCheckOut;

    public InventoryPage(){
        PageFactory.initElements(getDriver(), this);
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    public List<WebElement> getInventoryPrice() {

        return inventoryPrice;
    }


    public void sortInventoryByPriceLowToHigh() {
        Select sortDropdown = new Select(productContainer);
        sortDropdown.selectByVisibleText("Price (low to high)");
    }

    public void sortInventoryByPriceHighToLow() {
        Select sortDropdown = new Select(productContainer);
        sortDropdown.selectByVisibleText("Price (high to low)");
    }

    public boolean isInventoryListDisplayed() {
      boolean inventoryStatus = action.isDisplayed(getDriver(),inventory);
      return inventoryStatus;
    }


    public void addItemToCart(String itemName) {
        for (WebElement item : inventoryItems) {
            if (item.getText().contains(itemName)) {
                item.findElement(By.cssSelector("button.btn_inventory")).click();
                break;
            }
        }
    }
    public int getItemCount() {
        String itemCountText = cartBadge.getText();
        return Integer.parseInt(itemCountText);
    }

    public boolean removeItems(){
       boolean removeItemStatus = action.isDisplayed(getDriver(),removeItem);
       return removeItemStatus;
    }

    public CartPage navigateToCartPage(){
        action.click(getDriver(),clickCartToCheckOut);
        return new CartPage();
    }

}


