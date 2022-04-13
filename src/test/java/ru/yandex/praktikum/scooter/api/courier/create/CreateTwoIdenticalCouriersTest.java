package ru.yandex.praktikum.scooter.api.courier.create;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.scooter.api.courier.Courier;
import ru.yandex.praktikum.scooter.api.courier.CourierClient;
import ru.yandex.praktikum.scooter.api.courier.CourierCredentials;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class CreateTwoIdenticalCouriersTest {
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
    @DisplayName("The user can't create two identical couriers")
    public void createTwoIdenticalCouriers() {

        courierClient.create(courier);
        ValidatableResponse login = courierClient.login(CourierCredentials.from(courier));
        courierId = login.extract().path("id");
        ValidatableResponse response = courierClient.create(courier);
        int statusCode = response.extract().statusCode();
        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password)).extract().path("id");
        boolean isIdenticalCourierNotCreated = response.extract().path("message").equals("Этот логин уже используется");
        assertThat("Некорректный код статуса", statusCode, equalTo(409));
        assertTrue("Создано два одинаковых курьера", isIdenticalCourierNotCreated);

        //Баг в message:
        //ОР: "Этот логин уже используется"
        //ФР: "Этот логин уже используется. Попробуйте другой."
    }

}

