package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Register {

    private final WebDriver driver;
    // Поле для ввода Имя
    private final By nameField = By.xpath(".//label[text() = 'Имя']/parent::div/input");
    // Поле для ввода емайла
    private final By emailField = By.xpath(".//label[text() = 'Email']/parent::div/input");
    // Поле для ввода пароля
    private final By passwordField = By.xpath(".//input[@name = 'Пароль']");
    // Клик по кнопке "Зарегистрироваться"
    private final By registerButton = By.xpath(".//*[text() = 'Зарегистрироваться']");
    // Ошибка
    private final By error = By.xpath(".//fieldset[3]/div/p[contains(text(), 'Некорректный пароль')]");
    // Клик по кнопке "Войти"
    private final By aLinkLogin = By.xpath(".//a[text() = 'Войти']");

    public Register(WebDriver driver) {
        this.driver = driver;
    }
    // Открытие страницы для регистрации
    @Step("Opening the registration page")
    public void openRegisterPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }
    // Создание пользователя
    @Step("Creating a user")
    public void createUser(String name, String email, String password) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(registerButton).click();
    }
    // Клик по кнопке "Войти" при регистрации
    @Step("Logging in using the Voiti button in the registration form")
    public void clickALinkLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.elementToBeClickable(aLinkLogin));
        driver.findElement(aLinkLogin).click();
    }
    // Ошибка для некорректного пароля, мин. кол. символов 6
    @Step("Error for incorrect password. the password is 6 characters long")
    public void getError() {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOfElementLocated(error));
        driver.findElement(error).isDisplayed();
    }
    // Тестирование ошибки
    @Step("Check the error text for an incorrect password")
    public String getTextError() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(error));
        return driver.findElement(error).getText();
    }
}