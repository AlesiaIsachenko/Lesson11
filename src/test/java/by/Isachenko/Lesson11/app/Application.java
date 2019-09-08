package by.Isachenko.Lesson11.app;

import by.Isachenko.Lesson11.pages.CheckoutPage;
import by.Isachenko.Lesson11.pages.DuckPage;
import by.Isachenko.Lesson11.pages.MainPage;
import by.Isachenko.Lesson11.tests.MyListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Application {
    private EventFiringWebDriver driver;
    private WebDriverWait wait;

    private MainPage mainPage;
    private DuckPage duckPage;
    private CheckoutPage checkoutPage;

    By orderSummaru = By.cssSelector("#order_confirmation-wrapper");
    By iconDuckLocator = By.cssSelector(".shortcut a");
    By noProductMessage = By.cssSelector("em");

    public Application() {
        //driver = new EventFiringWebDriver(new ChromeDriver());
        //driver = new EventFiringWebDriver(new FirefoxDriver());
        driver = new EventFiringWebDriver(new EdgeDriver());
        driver.register(new MyListener());

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        duckPage = new DuckPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addDucksToCart(int quantity) {
        mainPage.open();
        List<WebElement> latestproductsList = mainPage.getLatestProductsList();
        for (int i = 0; i < quantity; i++) {
            System.out.println("Info: " + "Сlick on the product.");
            mainPage.clickDuck(latestproductsList.get(i));
            String textContentAttribute = duckPage.getTextContentNumCart();
            duckPage.addDuckToCart();
            duckPage.waitRoloadCart(textContentAttribute);
            duckPage.goToMainPage();
            latestproductsList = mainPage.getLatestProductsList();
        }
    }

    public void deleteAllDucksFromCart(){
        mainPage.clickCheckout();
        if (checkoutPage.areElementsPresent(orderSummaru)){
            if (checkoutPage.areElementsPresent(iconDuckLocator)){
                int sizeList = checkoutPage.getProductList().size();
                for (int i = 0; i<sizeList-1; i++){
                    checkoutPage.clickProduct(checkoutPage.getProductList().get(0));
                    int size2 = checkoutPage.getProductTableList().size();
                    checkoutPage.clickRemove();
                    //ожидаю обновления таблицы
                    checkoutPage.waitUpdateTable(size2);
                }
            }
            checkoutPage.clickRemove();
            assertTrue(checkoutPage.isElementPresent(noProductMessage));
        }

    }

}
