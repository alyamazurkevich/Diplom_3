package api;

import data.UserData;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserApi {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private static final String REGISTER = "api/auth/register";
    private static final String DELETE= "api/auth/user";
    private static final String LOGIN = "api/auth/login";

    UserData userData = new UserData();

    // Регистрация пользователя
    @Step("User registration")
    public Response createUser(UserData userData) {
        return given()
                .contentType("application/json")
                .body(userData)
                .when()
                .post(REGISTER);
    }
    // Удаление пользователя
    @Step("Deleting a user")
    public Response deleteUser(String UserAccessToken) {
        return given()
                .contentType("application/json")
                .header("Authorization", UserAccessToken)
                .when()
                .delete(DELETE);
    }
    // Авторизация пользователя
    @Step("User authorization")
    public Response loginUser(UserData userData) {
        return given()
                .contentType("application/json")
                .body(userData)
                .when()
                .post(LOGIN);
    }
}
