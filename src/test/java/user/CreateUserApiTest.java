package user;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateUserApiTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void createUniqueUser() {
        String email = "unique" + System.currentTimeMillis() + "@test.com";
        String payload = String.format("{\"email\": \"%s\", \"password\": \"123456\", \"name\": \"Test\"}", email);

        given().header("Content-type", "application/json")
                .body(payload)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue());
    }

    @Test
    public void createAlreadyRegisteredUser() {
        String email = "duplicate@test.com";
        String payload = String.format("{\"email\": \"%s\", \"password\": \"123456\", \"name\": \"Test\"}", email);

        // Create once
        given().header("Content-type", "application/json")
                .body(payload)
                .post("/api/auth/register");

        // Try again
        given().header("Content-type", "application/json")
                .body(payload)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(403)
                .body("message", equalTo("User already exists"));
    }

    @Test
    public void createUserWithMissingField() {
        String payload = "{\"email\": \"missing@test.com\", \"password\": \"123456\"}";

        given().header("Content-type", "application/json")
                .body(payload)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(403)
                .body("message", equalTo("Email, password and name are required fields"));
    }
}
