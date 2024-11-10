import driver.Driver;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import pages.Login;
import pages.Main;
import pages.Password;
import pages.Register;
import data.UserData;
import api.UserApi;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    private UserApi userApi;
    public String userEmail = "persivald@yandex.ru";
    public String userName = "Pers";
    public String userPassword = "perspers";
    public String userAccessToken;
    private Register objectRegister;
    private Login objectLogin;
    private WebDriver driver;

    @Before
    public void setUp() {
        RestAssured.baseURI = UserApi.BASE_URI;
        RestAssured.filters(new AllureRestAssured());
        userApi = new UserApi();
        UserData userData = new UserData(userEmail, userPassword, userName);
        userApi.createUser(userData);
        UserData userDataLogin = new UserData(userEmail, userPassword, null);
        Response response = userApi.loginUser(userDataLogin);
        userAccessToken = response.then().extract().path("accessToken");
        driver = Driver.createWebDriver();
        objectRegister = new Register(driver);
    }

    @After
    public void tearDown() {
        if (userAccessToken != null && !userAccessToken.isEmpty()) {
            userApi.deleteUser(userAccessToken);
        }
        driver.quit();
    }
    // Клик по кнопке "Войти"
    @Test
    @DisplayName("Log in using the Log in to account button on the main page")
    public void mainTest() {
        Main objectMain = new Main(driver);
        objectMain.openMainPage();
        objectMain.checkAuthorization();
        objectLogin = new Login(driver);
        objectLogin.login(userEmail, userPassword);
        assertEquals("Error", "Войти", objectMain.checkOrderButton());
    }
    // Клик на кнопку "Личный кабинет"
    @Test
    @DisplayName("Login via the Personal account button")
    public void accountT() {
        Main objectMain= new Main(driver);
        objectMain.openMainPage();
        objectMain.checkPersonalAccount();
        objectLogin = new Login(driver);
        objectLogin.login(userEmail, userPassword);
        assertEquals("Error", "Войти", objectMain.checkOrderButton());
    }
    // Клик на кнопку "Войти" при регистрации
    @Test
    @DisplayName("Login via the button in the registration form")
    public void registrationClickLink() {
        objectRegister.openRegisterPage();
        objectRegister.clickALinkLogin();
        Login objectLogin = new Login(driver);
        objectLogin.login(userEmail, userPassword);
        Main objectMain = new Main(driver);
        assertEquals("Error", "Войти", objectMain.checkOrderButton());
    }
    // Клик на кнопку "Вход" при восстановлении пароля
    @Test
    @DisplayName("Login via the button in the password recovery form")
    public void restorePasswordClickLink() {
        Password objectPassword = new Password(driver);
        objectPassword.openRestore();
        objectPassword.clickPassword();
        Login objectLogin = new Login(driver);
        objectLogin.login(userEmail, userPassword);
        Main objectMain = new Main(driver);
        assertEquals("Error", "Войти", objectMain.checkOrderButton());
    }
}
