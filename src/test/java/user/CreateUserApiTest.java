package user;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.config.BaseURL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateUserApiTest {

    private String accessToken;

    @Before
    public void setup() {
        RestAssured.baseURI = BaseURL.BASE_URL;
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешное создание уникального пользователя")
    @Description("Регистрация пользователя.Ожидается статус 200 и accessToken в ответе.")
    public void createUniqueUser() {
        String email = generateUniqueEmail();
        String payload = String.format("{\"email\": \"%s\", \"password\": \"123456\", \"name\": \"Test\"}", email);

        Response response = registerUser(payload);
        response.then().statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue());

        accessToken = extractAccessToken(response);
    }

    @Test
    @DisplayName("Нельзя создать уже зарегистрированного пользователя")
    @Description("Проверка невозможности повторной регистрации с тем же email. Ожидается статус 403 и сообщение об ошибке.")
    public void createAlreadyRegisteredUser() {
        String email = "duplicate_" + System.currentTimeMillis() + "@test.com";
        String payload = String.format("{\"email\": \"%s\", \"password\": \"123456\", \"name\": \"Test\"}", email);

        // Первая регистрация
        registerUser(payload);

        // Повторная попытка
        Response response = registerUser(payload);
        response.then().statusCode(403)
                .body("message", equalTo("User already exists"));
    }

    @Test
    @DisplayName("Ошибка при отсутствии обязательного поля name")
    @Description("Проверка регистрации без поля name. Ожидается статус 403 и сообщение об ошибке.")
    public void createUserWithMissingField() {
        String payload = "{\"email\": \"missing@test.com\", \"password\": \"123456\"}";

        Response response = registerUser(payload);
        response.then().statusCode(403)
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Step("Генерация уникального email")
    private String generateUniqueEmail() {
        return "user" + System.currentTimeMillis() + "@test.com";
    }

    @Step("Регистрация пользователя с телом запроса: {payload}")
    private Response registerUser(String payload) {
        return given()
                .header("Content-type", "application/json")
                .body(payload)
                .when()
                .post("/api/auth/register");
    }

    @Step("Извлечение accessToken из ответа")
    private String extractAccessToken(Response response) {
        return response.jsonPath().getString("accessToken").replace("Bearer ", "");
    }

    @Step("Удаление пользователя по accessToken")
    private void deleteUser(String token) {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/api/auth/user")
                .then()
                .statusCode(202); // предполагаемый код успешного удаления
    }
}
