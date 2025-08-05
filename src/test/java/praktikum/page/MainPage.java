
package praktikum.page;

import org.openqa.selenium.*;

public class MainPage {
    private final WebDriver driver;

    private final By bunTab = By.xpath("//span[text()='Булки']/..");
    private final By sauceTab = By.xpath("//span[text()='Соусы']/..");
    private final By fillingTab = By.xpath("//span[text()='Начинки']/..");
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By accountButton = By.xpath("//p[text()='Личный Кабинет']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private void clickTab(By tab) {
        WebElement element = driver.findElement(tab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickBunTab() {
        clickTab(bunTab);
    }

    public void clickSauceTab() {
        clickTab(sauceTab);
    }

    public void clickFillingTab() {
        clickTab(fillingTab);
    }

    public boolean isBunTabActive() {
        return driver.findElement(bunTab).getAttribute("class").contains("current");
    }

    public boolean isSauceTabActive() {
        return driver.findElement(sauceTab).getAttribute("class").contains("current");
    }

    public boolean isFillingTabActive() {
        return driver.findElement(fillingTab).getAttribute("class").contains("current");
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }
}
