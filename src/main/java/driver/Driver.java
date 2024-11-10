package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {
    //Выбор браузера
    public static WebDriver createWebDriver() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            return chromeDriver();
        }
        switch (browser) {
            case "yandex":
                return yandexDriver();
            case "chrome":
            default:
                return chromeDriver();}
    }

    public static WebDriver chromeDriver() {
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }

    public static WebDriver yandexDriver() {
        System.setProperty ("webdriver.chrome.driver", "src\\test\\resources\\yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }
}