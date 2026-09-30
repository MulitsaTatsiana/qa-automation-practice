package by.t366.study.api;

import by.t366.study.api.base.BaseTest;
import by.t366.study.api.model.CreateUserRequest;
import by.t366.study.api.model.CreateUserResponse;
import by.t366.study.api.model.ValidationError;
import by.t366.study.api.model.ValidationErrorResponse;
import by.t366.study.api.service.UserService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

public class Experement extends BaseTest {

    @Test
    void createUser() {
        CreateUserRequest request = CreateUserRequest.defaultUser();
        RequestSpecification spec = RestAssured.given()
                .contentType("application/json")
                .body(request);

        Response response = spec.when()
                .post();

        CreateUserResponse actual = response.then()
                .statusCode(200)
                .extract().as(CreateUserResponse.class);
        assertThat(actual.getUser().getId()).isNotNull();
        assertThat(actual.getUser().getEmail()).hasSizeBetween(1, 250);
        assertThat(actual.getUser().getLastName()).hasSize(1);
        assertThat(actual.getUser().getFirstName()).hasSize(1);
        assertThat(actual.getUser().getMiddleName()).hasSize(1);

    }

    @Test
    void createUser2() {
        CreateUserRequest request = CreateUserRequest.defaultUser2();
        RequestSpecification spec = RestAssured.given()
                .contentType("application/json")
                .body(request);

        Response response = spec.when()
                .post();

        CreateUserResponse actual = response.then()
                .statusCode(200)
                .extract().as(CreateUserResponse.class);
        assertThat(actual.getUser().getId()).isNotNull();
        assertThat(actual.getUser().getEmail()).hasSizeBetween(1, 250);
        assertThat(actual.getUser().getLastName()).hasSizeBetween(1, 50);
        assertThat(actual.getUser().getFirstName()).hasSizeBetween(1, 50);
        assertThat(actual.getUser().getMiddleName()).hasSizeBetween(1, 50);

    }

    @Test
    void createUser3() {
        CreateUserRequest request = CreateUserRequest.defaultUser3();
        RequestSpecification spec = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        Response response = spec.when()
                .post();

        CreateUserResponse actual = response.then()
                .statusCode(200)
                .extract().as(CreateUserResponse.class);
        String userId = actual.getUser().getId();
        assertThat(actual.getUser().getId()).isNotNull();
        assertThat(actual.getUser().getEmail()).hasSizeBetween(1, 250);
        assertThat(actual.getUser().getLastName()).hasSizeBetween(1, 50);
        assertThat(actual.getUser().getFirstName()).hasSizeBetween(1, 50);
        assertThat(actual.getUser().getMiddleName()).hasSizeBetween(1, 50);

    }

    @Test
    void getUser() {
        Response getResponse = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users/c87f7d03-697b-42bf-9395-777ab6e3fa59")
                .get();
    }

    @Test
    void createUser4() {
        CreateUserRequest request = CreateUserRequest.defaultUser4();
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
        assertThat(actual.getUser().getEmail()).hasSizeBetween(1, 250);
        assertThat(actual.getUser().getLastName()).hasSizeBetween(1, 50);
        assertThat(actual.getUser().getFirstName()).hasSizeBetween(1, 50);
        assertThat(actual.getUser().getMiddleName()).hasSizeBetween(1, 50);

    }

    @Test
    void createUser5() {
        CreateUserRequest request = CreateUserRequest.defaultUser5();
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
        assertThat(actual.getUser().getEmail()).hasSizeBetween(1, 250);
        assertThat(actual.getUser().getLastName()).hasSizeBetween(1, 50);
        assertThat(actual.getUser().getFirstName()).hasSizeBetween(1, 50);
        assertThat(actual.getUser().getMiddleName()).hasSizeBetween(1, 50);

    }

    @Test
    void createUser6() {
        CreateUserRequest request = CreateUserRequest.defaultUser6();
        RequestSpecification spec = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        Response response = spec.when()
                .post();

        response.then()
                .statusCode(422)
                .body("detail", notNullValue());

    }

    @ParameterizedTest
    @ValueSource(strings = {
            "john", "jack"
    })
    void createUser10(String name) {
        CreateUserRequest request = CreateUserRequest.defaultUser10(name + UUID.randomUUID() + "@test.com", "ghhh", "fcrdy", "fcdff", "hggg");
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

    }

    @ParameterizedTest
    @CsvSource({
            "ghu@test.com,ghhh,ffgh,jhgg,bcbsh",
            "ghuhbhbi@test.com,ghhh,ffgh,jhgg,bcbsh",
            "ghubgcsxs@test.com,ghhh,ffgh,jhgg,bcbsh"
    })
    void createUserFromCsv(String email, String password, String lastName, String firstName, String middleName) {
        CreateUserRequest request = CreateUserRequest.userCsv(email, password, lastName, firstName, middleName);

        RequestSpecification spec = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        Response response = spec.when()
                .post();

        CreateUserResponse actual = response.then()
                .statusCode(200)
                .extract().as(CreateUserResponse.class);
    }

    @ParameterizedTest
    @MethodSource("by.t366.study.api.model.CreateUserRequest#positiveUserMethodSource")
    void createPositiveUserMethodSource(String email, String password, String lastName, String firstName, String middleName) {
        CreateUserRequest request = CreateUserRequest.defaultUser10(email, password, lastName, firstName, middleName);

        UserService service = new UserService();

        CreateUserResponse response = service.createUser(request, SC_OK);
        assertThat(response.getUser().getId()).isNotNull();
        assertThat(response.getUser().getEmail()).isNotNull();
        assertThat(response.getUser().getLastName()).isNotNull();
        assertThat(response.getUser().getFirstName()).isNotNull();
        assertThat(response.getUser().getMiddleName()).isNotNull();

        assertThat(response.getUser().getId()).isNotEmpty();
        assertThat(response.getUser().getEmail()).isNotEmpty();
        assertThat(response.getUser().getLastName()).isNotEmpty();
        assertThat(response.getUser().getFirstName()).isNotEmpty();
        assertThat(response.getUser().getMiddleName()).isNotEmpty();

        assertThat(response.getUser().getEmail()).isEqualTo(email);
        assertThat(response.getUser().getLastName()).isEqualTo(lastName);
        assertThat(response.getUser().getFirstName()).isEqualTo(firstName);
        assertThat(response.getUser().getMiddleName()).isEqualTo(middleName);

        assertThat(response.getUser().getEmail()).hasSizeBetween(1, 250);
        assertThat(response.getUser().getLastName()).hasSizeBetween(1, 50);
        assertThat(response.getUser().getFirstName()).hasSizeBetween(1, 50);
        assertThat(response.getUser().getMiddleName()).hasSizeBetween(1, 50);

        assertThat(response.getUser().getEmail()).contains("@");
        assertThat(response.getUser().getEmail()).contains(".com");

//        RequestSpecification spec = RestAssured.given()
//                .baseUri("http://localhost:8000/api/v1/users")
//                .contentType("application/json")
//                .body(request);
//
//        Response response = spec.when()
//                .post();
//
//        CreateUserResponse actual = response.then()
//                .statusCode(200)
//                .extract().as(CreateUserResponse.class);
    }

    @ParameterizedTest
    @MethodSource("by.t366.study.api.model.CreateUserRequest#negativeUserMethodSource")
    void createNegativeUserMethodSource(String email, String password, String lastName, String firstName, String middleName) {
        CreateUserRequest request = CreateUserRequest.defaultUser10(email, password, lastName, firstName, middleName);
        UserService service = new UserService();

        ValidationErrorResponse response = service.createUserWithError(request, SC_UNPROCESSABLE_ENTITY);
        ValidationError error = response.getDetail().get(0);

        assertThat(response.getDetail()).isNotEmpty();
        assertThat(error.getType()).isNotEmpty();
        assertThat(error.getLoc()).first().isEqualTo("body");
        assertThat(error.getMsg()).isNotEmpty();
        // assertThat(error.getCtx()).isNotNull();
    }

    @ParameterizedTest
    @NullSource
    void createUserNullSource(String email) {
        CreateUserRequest request = CreateUserRequest.builder()
                .email(email)
                .password("12341")
                .lastName("John")
                .firstName("Ronin")
                .middleName("Vladimirovich")
                .build();

        RequestSpecification spec = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        Response response = spec.when()
                .post();

        response.then()
                .statusCode(422);
    }

    @ParameterizedTest
    @EmptySource
    void createUserEmptySourse(String email) {
        CreateUserRequest request = CreateUserRequest.defaultUser10(email, "hfg", "feew", "fdb", "sj");
        RequestSpecification spec = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        Response response = spec.when()
                .post();

        response.then()
                .statusCode(422);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void createUserNullAndEmptySource(String email) {
        CreateUserRequest request = CreateUserRequest.defaultUser10("", "jggy", "hgygff", "hcdwa", "sss");
        RequestSpecification spec = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        Response response = spec.when()
                .post();

        response.then()
                .statusCode(422);
    }

}
