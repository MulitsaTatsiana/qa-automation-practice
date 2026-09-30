package by.t366.study.api.service;

import by.t366.study.api.model.CreateUserRequest;
import by.t366.study.api.model.CreateUserResponse;
import by.t366.study.api.model.UpdateUserRequest;
import by.t366.study.api.model.ValidationErrorResponse;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserService {

    @Step("Создать пользователя: /api/v1/users")
    public CreateUserResponse createUser(CreateUserRequest request, int code) {
        RequestSpecification spec = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        Response response = spec.when()
                .post();

        return response.then()
                .statusCode(code)
                .extract().as(CreateUserResponse.class);
    }

    @Step("Обновить пользователя: /api/v1/users/{id}")


    public CreateUserResponse updateUser(String id, UpdateUserRequest request, int code) {
        RequestSpecification spec = RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        Response response = spec.when()
                .patch("/{id}", id);

        return response.then()
                .statusCode(code)
                .extract().as(CreateUserResponse.class);
    }

    public ValidationErrorResponse createUserWithError(CreateUserRequest request, int code) {
        RequestSpecification spec=RestAssured.given()
                .baseUri("http://localhost:8000/api/v1/users")
                .contentType("application/json")
                .body(request);

        Response response=spec.when()
                .post();

        return response.then()
                .statusCode(code)
                .extract().as(ValidationErrorResponse.class);



    }
}