
package praktikum.testcases;

import praktikum.base.BaseTest;
import praktikum.page.RegisterPage;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    public void successRegistration() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        String email = "test" + System.currentTimeMillis() + "@example.com";
        new RegisterPage(driver).register("Test User", email, "123456");
    }

    @Test
    public void errorWhenShortPassword() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        new RegisterPage(driver).register("Test User", "short@example.com", "123");
        assertTrue(new RegisterPage(driver).getErrorMessage().contains("Некорректный пароль"));
    }
}
