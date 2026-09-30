package by.t366.study.api;

import by.t366.study.api.base.BaseTest;
import by.t366.study.api.model.CreateUserRequest;
import by.t366.study.api.model.CreateUserResponse;
import by.t366.study.api.model.UpdateUserRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateUser extends BaseTest {

    private static final String BASE_URI = "http://localhost:8000/api/v1";

    private String userId;
    private String userEmail;
    private String userPassword;
    private String accessToken;

    @BeforeEach
    void setUp() {
        userPassword = "12341";
        CreateUserRequest newUser = CreateUserRequest.newUser();
        newUser.setPassword(userPassword);

        userId = RestAssured.given()
                .baseUri(BASE_URI + "/users")
                .contentType("application/json")
                .body(newUser)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().as(CreateUserResponse.class)
                .getUser().getId();

        userEmail = newUser.getEmail();

        accessToken = RestAssured.given()
                .baseUri(BASE_URI + "/authentication/login")
                .contentType("application/json")
                .body("{\"email\": \"" + userEmail + "\", \"password\": \"" + userPassword + "\"}")
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token.accessToken");
    }

    @ParameterizedTest
    @MethodSource("by.t366.study.api.model.UpdateUserRequest#positiveUserUpdate")
    void shouldUpdateUserSuccessfully(UpdateUserRequest body) {
        Response response = RestAssured.given()
                .baseUri(BASE_URI + "/users")
                .contentType("application/json")
                .header("Authorization", "Bearer " + accessToken)
                .body(body)
                .when()
                .patch("/{userId}", userId);

        CreateUserResponse updated = response.then()
                .statusCode(200)
                .extract().as(CreateUserResponse.class);

        if (body.getEmail() != null) {
            assertThat(updated.getUser().getEmail()).isEqualTo(body.getEmail());
        }
        if (body.getFirstName() != null) {
            assertThat(updated.getUser().getFirstName()).isEqualTo(body.getFirstName());
        }
        if (body.getLastName() != null) {
            assertThat(updated.getUser().getLastName()).isEqualTo(body.getLastName());
        }
        if (body.getMiddleName() != null) {
            assertThat(updated.getUser().getMiddleName()).isEqualTo(body.getMiddleName());
        }
    }
}
