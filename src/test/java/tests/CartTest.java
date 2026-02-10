package tests;

import org.testng.annotations.Test;

import static enums.TitleNaming.CART;
import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {
    final String goodsName = "Sauce Labs Fleece Jacket";

    @Test
    public void checkGoodsAdded() {
        System.out.println("CartTest.correct ! in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertEquals(productsPage.checkTitleName(), PRODUCTS.getDisplayName());

        productsPage.addGoodsToCart(goodsName);
        productsPage.addGoodsToCart(3);
        productsPage.switchToCart();

        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.checkTitleName(), CART.getDisplayName());
        assertEquals(cartPage.getProductsNames().size(), 2);
        assertTrue(cartPage.getProductsNames().contains(goodsName));
    }
}
