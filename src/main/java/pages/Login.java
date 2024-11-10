package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {
    private final WebDriver driver;
    // Поле для ввода емайла
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    // Поле для ввода пароля
    private final By passwordField = By.xpath("//input[@type='password']");
    // Клик на кнопку "Войти"
    private final By loginButtonV = By.xpath(".//button [text()='Войти']");

    public Login(WebDriver driver) {//конструктор класса LoginPage
        this.driver = driver;
    }
    // Авторизация
    @Step("Passing authorization")
    public void login(String email, String password) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }

        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginButtonV));
        driver.findElement(loginButtonV).click();
    }
    // Проверка видимости кнопки "Войти"
    @Step("Checking the visibility of the voiti button")
    public String checkLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(17))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButtonV));
        return driver.findElement(loginButtonV).getText();
    }
}