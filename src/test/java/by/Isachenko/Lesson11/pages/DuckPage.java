package by.Isachenko.Lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DuckPage extends Page {
    @FindBy(css="#cart .quantity")
    public WebElement numCart;

    @FindBy(css="#logotype-wrapper a")
    public WebElement hrefMainPage;

    @FindBy(css="[name='options[Size]']")
    public WebElement size;

    By sizeBy = By.cssSelector("[name='options[Size]']");
    By addCartProductButtun = By.cssSelector("[name=add_cart_product]");

    public DuckPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public DuckPage setSize(String newSize){
        Select dropdown = new Select(size);
        dropdown.selectByVisibleText(newSize);
        System.out.println("Info: " + "Set size -- " + newSize + ".");
        return this;
    }

    public String getTextContentNumCart(){
        return numCart.getAttribute("textContent");
    }

    public void waitRoloadCart(String textContentAttribute){
        wait.until((WebDriver driver)->{
                    String attribute = getTextContentNumCart();
                    return textContentAttribute.compareTo(attribute)!=0 ? true:false;
                }
        );
    }

    public DuckPage clickAddToCart (){
        driver.findElement(addCartProductButtun).click();
        System.out.println("Info: " + "Click -- add cart product.");
        return this;
    }

    public DuckPage addDuckToCart(){
        if (areElementsPresent(sizeBy)){
            setSize("Small");
        }
        clickAddToCart();
        return this;
    }

    public void goToMainPage(){
        hrefMainPage.click();
        System.out.println("Info: " + "Go to main page.");
    }
}
