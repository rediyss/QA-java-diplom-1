package praktikum.base;

import praktikum.driver.DriverHelper;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() throws IOException {
        DriverHelper driverHelper = new DriverHelper();
        driver = driverHelper.initDriver();
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
