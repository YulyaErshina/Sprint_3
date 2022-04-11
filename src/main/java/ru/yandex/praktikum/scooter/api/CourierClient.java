package ru.yandex.praktikum.scooter.api;

import static io.restassured.RestAssured.given;
import io.restassured.response.ValidatableResponse;

public class CourierClient extends ScooterRestClient {

    public final String COURIER_PATH = "/api/v1/courier";

    public ValidatableResponse create(Courier courier){
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    public ValidatableResponse login(CourierCredentials courierCredentials){
        return given()
                .spec(getBaseSpec())
                .body(courierCredentials)
                .when()
                .post(COURIER_PATH + "/login")
                .then();
    }

    public ValidatableResponse delete(int courierId) {
        return given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + courierId)
                .then();
    }
}
