package by.Isachenko.Lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends Page {
    @FindBy(css="#cart .link")
    public WebElement checkout;

    By latestProductsList = By.cssSelector("#box-latest-products .product");

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage open() {
        driver.navigate().to("https://litecart.stqa.ru/en/");
        return this;
    }

    public List<WebElement> getLatestProductsList(){
        return driver.findElements(latestProductsList);
    }

    public void clickDuck(WebElement element){
        element.click();
    }

    public void clickCheckout(){
        checkout.click();
        System.out.println("Info: " + "Click Checkout.");
    }
}
