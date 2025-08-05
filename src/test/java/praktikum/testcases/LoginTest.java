
package praktikum.testcases;

import praktikum.base.BaseTest;
import praktikum.page.LoginPage;
import praktikum.page.MainPage;
import praktikum.page.ForgotPasswordPage;

import org.junit.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginFromMainPage() {
        new MainPage(driver).clickLoginButton();
        new LoginPage(driver).login("test@example.com", "123456");
    }

    @Test
    public void loginFromAccountButton() {
        new MainPage(driver).clickAccountButton();
        new LoginPage(driver).login("test@example.com", "123456");
    }

    @Test
    public void loginFromForgotPasswordPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        new ForgotPasswordPage(driver).clickLoginLink();
        new LoginPage(driver).login("test@example.com", "123456");
    }
}
