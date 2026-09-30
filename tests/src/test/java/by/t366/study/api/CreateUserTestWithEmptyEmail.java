package by.t366.study.api;

import by.t366.study.api.model.CreateUserRequest;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTestWithEmptyEmail {
    @Test
    void createUserWithEmptyEmail() {
        RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body("{\"email\": \"\", \"password\": \"12341\",\"lastName\": \"John\",\"firstName\": \"Ronin\",\"middleName\": \"Vladimirovich\"}")
                .when()
                .post()
                .then()
                .statusCode(422)
                .body("user.email", equalTo(null));
    }

    @Test
    void createUserWithEmptyEmail2() {

        CreateUserRequest request = CreateUserRequest.builder()
                .email("")
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
                .statusCode(422)
                .body("user.email", equalTo(null));
    }

    @Test
    void createUserWithEmptyEmail3() {

        CreateUserRequest request = CreateUserRequest.createUserWithInvalidEmailTest("");

        RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(422)
                .body("user.email", equalTo(null));
    }

    @Test
    void createUserWithEmptyEmail4() {

        CreateUserRequest request = CreateUserRequest.createUserWithInvalidEmailTest("");

        RequestSpecification spec = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        spec.when()
                .post()

                .then()
                .statusCode(422)
                .body("detail", notNullValue());
    }
}
