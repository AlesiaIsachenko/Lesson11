package by.Isachenko.Lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeLessThan;

public class CheckoutPage extends Page{
    @FindBy(name="remove_cart_item")
    public WebElement removeButton;

    By iconDuckLocator = By.cssSelector(".shortcut a");
    By productTableList = By.cssSelector("td.item");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProductList (){
        return driver.findElements(iconDuckLocator);
    }

    public CheckoutPage clickProduct(WebElement duck){
        duck.click();
        return this;
    }

    public CheckoutPage clickRemove(){
        removeButton.click();
        System.out.println("Info: " + "Click Remove.");
        return this;
    }

    public List<WebElement> getProductTableList (){
        return driver.findElements(productTableList);
    }

    public void waitUpdateTable(int size){
        wait.until(numberOfElementsToBeLessThan(productTableList, size));
    }
}
