package ru.yandex.praktikum.scooter.api.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.scooter.api.ScooterRestClient;

import static io.restassured.RestAssured.given;

public class OrderClient extends ScooterRestClient {

    public final String ORDER_PATH = "/api/v1/orders";

    @Step("Создание нового заказа")
    public ValidatableResponse createNewOrder(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step("Отмена заказа")
    public void cancel(int track) {
        given()
                .spec(getBaseSpec())
                .body(track)
                .when()
                .put(ORDER_PATH + "/cancel")
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getListOrders() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }
}

