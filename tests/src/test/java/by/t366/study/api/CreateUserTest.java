package by.t366.study.api;

import by.t366.study.api.model.CreateUserRequest;
import by.t366.study.api.model.CreateUserResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;


class CreateUserTest {

    @Test
    void shouldCreateUserSuccessfully() {
        String email = "john" + UUID.randomUUID() + "@test.com";
        RestAssured
                .given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"12341\",\"lastName\": \"John\",\"firstName\": \"Ronin\",\"middleName\": \"Vladimirovich\"}")

                .when()
                .post()


                .then()
                .statusCode(200)
                .body("user.id", notNullValue())
                .body("user.email", equalTo(email))
                .body("user.lastName", equalTo("John"))
                .body("user.firstName", equalTo("Ronin"))
                .body("user.middleName", equalTo("Vladimirovich"));


    }

    @Test
    void shouldCreateUserSuccessfully2() {
        String email = "john" + UUID.randomUUID() + "@test.com";
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
                .statusCode(200)
                .body("user.id", notNullValue())
                .body("user.email", equalTo(email))
                .body("user.lastName", equalTo("John"))
                .body("user.firstName", equalTo("Ronin"))
                .body("user.middleName", equalTo("Vladimirovich"));
    }

    @Test
    void shouldCreateUserSuccessfully3() {
        CreateUserRequest request = CreateUserRequest.newUser();

        RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("user.id", notNullValue())
                .body("user.email", equalTo(request.getEmail()))
                .body("user.lastName", equalTo("John"))
                .body("user.firstName", equalTo("Ronin"))
                .body("user.middleName", equalTo("Vladimirovich"));
    }

    @Test
    void shouldCreateUserSuccessfully4() {
        CreateUserRequest request = CreateUserRequest.newUser();


        RequestSpecification spec = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        Response response = spec.when()
                .post();

        CreateUserResponse actual = response.then()
                .statusCode(200)
                .extract().as(CreateUserResponse.class);

        assertThat(actual.getUser().getId()).isNotNull();
        assertThat(actual.getUser().getEmail()).isEqualTo(request.getEmail());
        assertThat(actual.getUser().getLastName()).isEqualTo(request.getLastName());
        assertThat(actual.getUser().getFirstName()).isEqualTo(request.getFirstName());
        assertThat(actual.getUser().getMiddleName()).isEqualTo(request.getMiddleName());

    }
}
