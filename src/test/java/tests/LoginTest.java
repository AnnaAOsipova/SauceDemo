package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {
    @Test
    public void correctLogin() {
        System.out.println("LoginTest.correct ! in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.isTitleIsDisplayed(), "Заголовок не виден");
        assertEquals(productsPage.checkTitleName(), PRODUCTS.getDisplayName(), "Не верный заголовок");
    }

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {UserFactory.withLockedPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {UserFactory.withEmptyLogin(), "Epic sadface: Username is required"},
                {UserFactory.withEmptyPassword(), "Epic sadface: Password is required"},
                {UserFactory.withIncorrectLogin(), "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Epic("Тестирование интернет площадки Saucedemo")
    @Feature("Проверка")
    @Story("Какой-то текст")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Anna Osipova anna@m.com")
    @TmsLink("SauceDemo")
    @Issue("Python_PhoneBook")
    @Test(dataProvider = "loginData", description = "тест проверяет авторизацию с некорректными или пустыми данными")
    public void incorrectLogin(User user, String errorMsg) {
        System.out.println("LoginTest.incorrect !!! in thread: "
                + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), errorMsg,
                "Не верный текст сообщение об ошибке");
    }
}
