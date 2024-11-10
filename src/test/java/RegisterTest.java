import api.UserApi;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import data.UserData;
import driver.Driver;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.Login;
import pages.Register;
import pages.Main;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class RegisterTest {
    private Register  objectRegister;
    private WebDriver driver;
    public String userAccessToken;
    public String userEmail = "persivald@yandex.ru";
    public String userName = "Pers";
    public String userPassword = "perspers";
    private UserApi userApi;

    // Открытие браузера и сайта. Создание пользователя
    @Before
    @DisplayName("Opening a browser, website and creating test user data")
    public void before() {
        driver = Driver.createWebDriver();
        objectRegister = new Register(driver);
    }
    // Регистрация и клик по кнопке "Вход"
    @Test
    @DisplayName("User registration and user login")
    public void registrationUser() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(17));
        objectRegister.openRegisterPage();
        objectRegister.createUser(userName, userEmail, userPassword);
        Login objectLogin = new Login(driver);
        objectLogin.login(userEmail, userPassword);
        Main objectMain = new Main(driver);
        assertEquals("Error", "Войти", objectMain.checkOrderButton());
    }
    // Проверка на ошибку при входе
    @Test
    @DisplayName("Error checking")
    public void checkError() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(17));
        objectRegister.openRegisterPage();
        objectRegister.createUser(userName, userEmail, "12345");
        objectRegister.getError();
        assertEquals("Error", "Некорректный пароль", objectRegister.getTextError());
    }

    @After
    public void tearDown() {
        RestAssured.baseURI = UserApi.BASE_URI;
        RestAssured.filters(new AllureRestAssured());
        userApi = new UserApi();
        UserData userDataLogin = new UserData(userEmail, userPassword, null);
        Response response = userApi.loginUser(userDataLogin);
        userAccessToken = response.then().extract().path("accessToken");
        if (userAccessToken != null && !userAccessToken.isEmpty()) {
            userApi.deleteUser(userAccessToken);
        }
        driver.quit();
    }
}
