package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Password {
    private final WebDriver driver;
    private final static String PasswordRecoveryPage = "https://stellarburgers.nomoreparties.site/login";
    // Клик "Восстановить пароль"
    private final By restoreButton = By.xpath(".//a[text()='Восстановить пароль']");
    // Клик переход по ссылке через кнопку "Войти"
    private final By loginButton = By.xpath(".//a[@class='Auth_link__1fOlj']");

    public Password(WebDriver driver) {// конструктор класса ForgotPasswordPage
        this.driver = driver;
    }
    //Восстановить пароль
    @Step("Opening the Vosstanovit parol page")
    public void openRestore() {
        driver.get(PasswordRecoveryPage);
    }
    // Клик по кнопке "Войти" в форме восстановления пароля
    @Step("Logging in using the Voiti button in the password recovery form")
    public void clickPassword() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(restoreButton));
        driver.findElement(restoreButton).click();
        driver.findElement(loginButton).click();
    }
}
