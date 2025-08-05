package praktikum.testcases;

import praktikum.base.BaseTest;
import praktikum.page.MainPage;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConstructorTabTest extends BaseTest {

    @Test
    public void userCanSwitchToBunsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickBunTab();
        assertTrue("Булки не активны", mainPage.isBunTabActive());
    }

    @Test
    public void userCanSwitchToSaucesTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceTab();
        assertTrue("Соусы не активны", mainPage.isSauceTabActive());
    }

    @Test
    public void userCanSwitchToFillingsTab() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingTab();
        assertTrue("Начинки не активны", mainPage.isFillingTabActive());
    }
}
