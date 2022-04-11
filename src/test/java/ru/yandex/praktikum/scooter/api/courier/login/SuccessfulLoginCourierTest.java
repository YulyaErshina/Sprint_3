package ru.yandex.praktikum.scooter.api.courier.login;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.scooter.api.courier.CourierClient;
import ru.yandex.praktikum.scooter.api.courier.Courier;
import ru.yandex.praktikum.scooter.api.courier.CourierCredentials;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SuccessfulLoginCourierTest {


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
    @DisplayName("Successful courier login")
    @Description("The courier can be authorized if all required fields are filled in")
    public void successfulLoginCourier(){

        courierClient.create(courier);

        ValidatableResponse response = courierClient.login(new CourierCredentials(courier.login, courier.password));

        courierId = response.extract().path("id");
        int statusCode = response.extract().statusCode();

        assertThat(statusCode, equalTo(200));
        assertThat("Id курьера некорректно", courierId, is(not(0)));

    }

}

