package tests;

import org.testng.annotations.Test;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Fleece Jacket", "Sauce Labs Onesie",
                    "Test.allTheThings() T-Shirt (Red)");

    @Test
    public void checkGoodsAdded() {
        System.out.println("ProductsTest.correct ! in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isTitleIsDisplayed());
        assertEquals(productsPage.checkTitleName(), PRODUCTS.getDisplayName());

        for (String goods : goodsList) {
            productsPage.addGoodsToCart(goods);
        }

        productsPage.addGoodsToCart(2);
        assertEquals(productsPage.checkCounterValue(), "4");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
