package praktikum.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverHelper {

    public WebDriver initDriver() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/browser.properties"));
        String browserProperty = properties.getProperty("testBrowser");
        BrowserType browserType = BrowserType.valueOf(browserProperty.toUpperCase());

        WebDriver driver;

        switch (browserType) {
            //Свитч на Хроме
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            //свитч на яндекс
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/yandexdriver.exe");

                ChromeOptions options = new ChromeOptions();
                String userHome = System.getProperty("user.home");
                String yandexPath = userHome + "\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
                options.setBinary(yandexPath);

                driver = new ChromeDriver(options);
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browserProperty);
        }

        return driver;
    }
}
