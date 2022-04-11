package ru.yandex.praktikum.scooter.api;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends ScooterRestClient {

    public final String ORDER_PATH = "/api/v1/orders";

    public ValidatableResponse createNewOrder(Order order){
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    public void cancel(int track) {
        given()
                .spec(getBaseSpec())
                .body(track)
                .when()
                .put(ORDER_PATH + "/cancel")
                .then();
    }

    public ValidatableResponse getListOrders(){
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }
}

