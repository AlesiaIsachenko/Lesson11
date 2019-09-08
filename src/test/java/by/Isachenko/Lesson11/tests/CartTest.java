package by.Isachenko.Lesson11.tests;

import org.junit.Test;

public class CartTest extends TestBase{
    @Test
    public void testCart() {
        app.addDucksToCart(3);
        app.deleteAllDucksFromCart();
        System.out.println("Test is over.");
    }
}
