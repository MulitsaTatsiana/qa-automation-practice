package by.t366.study.api;

import by.t366.study.api.model.CreateUserRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserWithInvalidEmailTest {

    @Test
    void createUserWithInvalidEmailMissingAt() {
        String email = "john" + UUID.randomUUID();
        RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"12341\",\"lastName\": \"John\",\"firstName\": \"Ronin\",\"middleName\": \"Vladimirovich\"}")
                .when()
                .post()
                .then()
                .statusCode(422)
                .body("user.email", not(containsString("@")));

    }

    @Test
    void createUserWithInvalidEmailMissingAt2() {
        String email = "john" + UUID.randomUUID();
        CreateUserRequest request = CreateUserRequest
                .builder()
                .email(email)
                .password("12341")
                .lastName("")
                .firstName("")
                .middleName("")
                .build();

        RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(422)
                .body("user.email", not(containsString("@")));
    }

    @Test
    void createUserWithInvalidEmailMissingAt3() {
        String email = "john" + UUID.randomUUID();

        CreateUserRequest request = CreateUserRequest.createUserWithInvalidEmailTest(email);

        RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(422)
                .body("user.email", not(containsString("@")));
    }

    @Test
    void createUserWithInvalidEmailMissingAt4() {

        CreateUserRequest request = CreateUserRequest.createUserWithInvalidEmailTest("john" + UUID.randomUUID());

        RequestSpecification spec = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        Response response = spec.when()
                .post();

        response.then()
                .statusCode(422)
                .body("detail", notNullValue())
                .body("detail[0].msg", containsString("email"));





    }
}