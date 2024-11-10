package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Profile {

    private final WebDriver driver;
    // Клик кнопка "Выход"
    private final By exitButton = By.className("Account_button__14Yp3");
    // Лого "Stellar burgers"
    private final By logoButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/' ]");
    // Клик кнопка "Конструктор"
    private final By constructorButton = By.xpath("//a[@href='/' and @class='AppHeader_header__link__3D_hX']");

    public Profile (WebDriver driver) { //констуктор класса ProfilePage
        this.driver = driver;
    }
    // Клик по кнопке "Выход"
    @Step("Log out of your personal account using the button Vyhod")
    public void clickExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.elementToBeClickable(exitButton));
        driver.findElement(exitButton).click();
    }
    // Переход на стартовую страницу через клик по лого <Stellar burgers>"
    @Step("Go to the start page using the Stellar burgers logo")
    public void clickLogoButton() {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.elementToBeClickable(logoButton));
        driver.findElement(logoButton).click();
    }
    // Переход на стартовую страницу в раздел "Конструктор" кликнув по кнопке "Конструктор"
    @Step("Go to the start page in the Konstruktor section by clicking the Konstruktor button")
    public void clickConstructorButton() {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
    }
    // Проверка кнопки "Выход"
    @Step("Checking the button Vyhod")
    public String checkClickLogInPersonalAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        return driver.findElement(exitButton).getText();
    }
}