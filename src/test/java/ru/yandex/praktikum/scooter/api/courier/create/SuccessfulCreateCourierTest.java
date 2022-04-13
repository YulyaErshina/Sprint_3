package ru.yandex.praktikum.scooter.api.courier.create;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.scooter.api.courier.Courier;
import ru.yandex.praktikum.scooter.api.courier.CourierClient;
import ru.yandex.praktikum.scooter.api.courier.CourierCredentials;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class SuccessfulCreateCourierTest {

    private CourierClient courierClient;
    private int courierId;
    private Courier courier;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = Courier.getRandomCourier();
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("The user can create a new courier")
    @Description("The user can create a new courier if he fills in all the required fields")
    public void successfulCourierCreation() {

        ValidatableResponse response = courierClient.create(courier);
        int statusCode = response.extract().statusCode();
        boolean isCourierCreated = response.extract().path("ok");
        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password)).extract().path("id");
        assertThat("Некорректный код статуса", statusCode, equalTo(201));
        assertTrue("Курьер не создан", isCourierCreated);
        assertThat("Неверный ID курьера", courierId, is(not(0)));
    }

}



