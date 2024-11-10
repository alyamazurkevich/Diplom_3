package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Main {
    public final WebDriver driver;

    private final static String URL = "https://stellarburgers.nomoreparties.site/";
    // Кнопка для входа в аккаунт
    private final By loginAccountButton = By.xpath(".//*[text() = 'Войти в аккаунт']");
    // Кнопка "Личный кабинет"
    private final By personalButton = By.xpath(".//*[text() = 'Личный Кабинет']");
    // Вкладка "Булки"
    private final By bunsButton = By.xpath(".//span[text()='Булки']/..");
    // Вкладка "Соусы"
    private final By sauceButton = By.xpath("//span[text()='Соусы']/..");
    // Вкладка "Начинки"
    private final By fillingButton = By.xpath("//span[text()='Начинки']/..");
    // Кнопка для оформления заказа
    private final By orderButton = By.className("button_button__33qZ0");

    private final By bunElement = By.xpath(".//*/div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");
    private final By sauceElement = By.xpath(".//*/div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");
    private final By fillingElement = By.xpath(".//*/div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");

    public Main (WebDriver driver) {
        this.driver = driver;
    }
    // Открытие главной страницы
    @Step("Opening the main page")
    public void openMainPage() {
        driver.get(URL);
    }
    //Войти в аккаунт
    @Step("Log in to your account on the main page")
    public void checkAuthorization() {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOfElementLocated(loginAccountButton));
        Object elementLoginAccountButton = driver.findElement(loginAccountButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementLoginAccountButton);
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.elementToBeClickable(loginAccountButton));
        driver.findElement(loginAccountButton).click();
    }
    //Вход в личный кабинет
    @Step("Log in to your personal account")
    public void checkPersonalAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(17))
                .until(ExpectedConditions.elementToBeClickable(personalButton));
        driver.findElement(personalButton).click();
    }
    // Проверки видимости кнопки "Оформить заказ"
    @Step("Loading the main page, displaying the Oformit zakaz button")
    public Object checkOrderButton() {
        WebElement textButton = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        return textButton.getText();
    }
    // Открыть вкладку "Булки"
    @Step("Opening the bulki roll tab")
    public boolean checkBuns() {
        driver.findElement(sauceButton).click();
        driver.findElement(bunsButton).click();
        return driver.findElement(bunElement).isDisplayed();
    }
    // Открыть вкладку "Соусы"
    @Step("Opening the sousy tab")
    public boolean checkSauce() {
        driver.findElement(sauceButton).click();
        return driver.findElement(sauceElement).isDisplayed();
    }
    // Открыть вкладку "Начинки"
    @Step("Opening a tab with nachinki")
    public boolean checkFillings() {
        driver.findElement(fillingButton).click();
        return driver.findElement(fillingElement).isDisplayed();
    }
}
