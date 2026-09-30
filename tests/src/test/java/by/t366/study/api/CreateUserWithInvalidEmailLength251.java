package by.t366.study.api;

import by.t366.study.api.model.CreateUserRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class CreateUserWithInvalidEmailLength251 {
    @Test
    void createUserWithInvalidEmailLength251() {
        String email = "a".repeat(251) + "@test.com";
        RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"12341\",\"lastName\": \"John\",\"firstName\": \"Ronin\",\"middleName\": \"Vladimirovich\"}")
                .when()
                .post()
                .then()
                .statusCode(422);

    }

    @Test
    void createUserWithInvalidEmailLength2512() {
        String email = "a".repeat(251) + "@test.com";

        CreateUserRequest request = CreateUserRequest
                .builder()
                .email(email)
                .password("12341")
                .lastName("John")
                .firstName("Ronin")
                .middleName("Vladimirovich")
                .build();

        RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(422);
    }

    @Test
    void createUserWithInvalidEmailLength2513() {
        String email = "a".repeat(251) + "@test.com";

        CreateUserRequest request = CreateUserRequest.userEmailLength251(email);

        RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(422);
    }

    @Test
    void createUserWithInvalidEmailLength2514() {
        String email = "a".repeat(251) + "@test.com";

        CreateUserRequest request = CreateUserRequest.userEmailLength251(email);

        RequestSpecification spec = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        Response response = spec
                .when()
                .post();

        response.then()
                .statusCode(422)
                .body("detail",notNullValue());
    }

}


