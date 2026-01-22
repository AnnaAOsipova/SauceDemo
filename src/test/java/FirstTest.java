import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import static org.openqa.selenium.Keys.COMMAND;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FirstTest extends BaseTest {
    @Test
    public void correctLogin() {
        // открыть браузер
        // зайти на сайт https://www.saucedemo.com

        browser.findElement(By.id("user-name")).sendKeys("standard_user");
        browser.findElement(By.id("user-name")).sendKeys(COMMAND + "A");
        browser.findElement(By.id("user-name")).sendKeys(Keys.BACK_SPACE);
        browser.findElement(By.id("user-name")).sendKeys("standard_user");
        browser.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        browser.findElement(By.cssSelector("[name='login-button']")).click();

        //boolean titleIsDisplayed = browser.findElement(By.cssSelector("[data-test='title']")).isDisplayed();
        assertTrue(browser.findElement(By.cssSelector("[data-test='title']")).isDisplayed(), "Заголовок не виден");

        String titleName = browser.findElement(By.cssSelector("[data-test='title']")).getText();
        assertEquals(titleName, "Products", "Не верный заголовок");
    }

    @Test
    public void incorrectLogin() {
        // открыть браузер
        // зайти на сайт https://www.saucedemo.com

        browser.findElement(By.id("user-name")).sendKeys("locked_out_user");
        browser.findElement(By.xpath("//*[@data-test='password']")).sendKeys("secret_sauce");
        browser.findElement(By.cssSelector("[name='login-button']")).click();

        assertTrue(browser.findElement(By.cssSelector("[data-test='error']")).isDisplayed(), "Нет сообщения об ошибке");

        assertEquals(browser.findElement(By.cssSelector("[data-test='error']")).getText(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Не верный текст сообщение об ошибке");
    }
}