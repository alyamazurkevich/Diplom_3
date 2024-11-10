import api.UserApi;
import io.restassured.response.Response;
import data.UserData;
import driver.Driver;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.Login;
import pages.Profile;
import pages.Main;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private WebDriver driver;
    public Login objectLogin;
    public String userEmail = "persivald@yandex.ru";
    public String userName = "Pers";
    public String userPassword = "perspres";
    public UserApi userApi;
    public String userAccessToken;
    // Открытие браузера и сайта. Создание пользователя
    @Before
    @DisplayName("Opening a browser, website and creating test user data")
    public void before() {
        RestAssured.baseURI = UserApi.BASE_URI;
        RestAssured.filters(new AllureRestAssured());
        userApi = new UserApi();
        UserData userData = new UserData(userEmail, userPassword, userName);
        userApi.createUser(userData);
        UserData userDataLogin = new UserData(userEmail, userPassword, null);
        Response response = userApi.loginUser(userDataLogin);
        userAccessToken = response.then().extract().path("accessToken");
        objectLogin = new Login(driver);
        driver = Driver.createWebDriver();
    }
    // Проверка логина
    @Test
    @DisplayName("Verification of the user's login")
    public void personalAccountLogin() {
        Main objectMain = new Main(driver);
        objectMain.openMainPage();
        objectMain.checkPersonalAccount();
        objectLogin = new Login(driver);
        objectLogin.login(userEmail, userPassword);
        objectMain.checkPersonalAccount();
        Profile objProfilePage = new Profile(driver);
        assertEquals("Entering was  Failed", "Выход", objProfilePage.checkClickLogInPersonalAccount());
    }
    // Проверка выхода из аккаунта
    @Test
    @DisplayName("Checking to log out of your account")
    public void checkExit() {
        Main objectMain = new Main(driver);
        objectMain.openMainPage();
        objectMain.checkPersonalAccount();
        objectLogin = new Login(driver);
        objectLogin.login(userEmail, userPassword);
        objectMain.checkPersonalAccount();
        Profile objProfilePage = new Profile(driver);
        objProfilePage.clickExitButton();
        assertEquals("ExitFailed", "Войти", objectLogin.checkLoginButton());
    }
    // Переход в личный кабинет
    @Test
    @DisplayName("Transfer to your personal account")
    public void checkLogo() {
        Main objectMain = new Main(driver);
        objectMain.openMainPage();
        objectMain.checkPersonalAccount();
        objectLogin = new Login(driver);
        objectLogin.login(userEmail, userPassword);
        objectMain.checkPersonalAccount();
        Profile objectProfile = new Profile(driver);
        objectProfile.clickLogoButton();
        assertEquals("LogoButtonFailed", "Оформить заказ", objectMain.checkOrderButton());
    }
    // Переход в конструктор из личного кабинета
    @Test
    @DisplayName("Switching from your personal account to the constructor")
    public void checkConstructor() {
        Main objectMain = new Main(driver);
        objectMain.openMainPage();
        objectMain.checkPersonalAccount();
        objectLogin = new Login(driver);
        objectLogin.login(userEmail, userPassword);
        objectMain.checkPersonalAccount();
        Profile objectProfile = new Profile(driver);
        objectProfile.clickConstructorButton();
        assertEquals("ConstructorButtonFailed", "Оформить заказ", objectMain.checkOrderButton());
    }

    @After
    public void tearDown() {
        if (userAccessToken != null && !userAccessToken.isEmpty()) {
            userApi.deleteUser(userAccessToken);
        }
        driver.quit();
    }
}
