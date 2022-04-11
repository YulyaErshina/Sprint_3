package ru.yandex.praktikum.scooter.api.courier.login;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.scooter.api.courier.CourierClient;
import ru.yandex.praktikum.scooter.api.courier.Courier;
import ru.yandex.praktikum.scooter.api.courier.CourierCredentials;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class LoginCourierWithoutRequiredFieldsTest {

    private static final CourierClient COURIER_CLIENT = new CourierClient();
    private static final Courier COURIER = Courier.getRandomCourier();
    private final CourierCredentials courierCredentials;
    private final int expStatusCode;
    private final String expErrorMessage;
    private int courierId;

    @After
    public void tearDown() {
        COURIER_CLIENT.delete(courierId);
    }

    public LoginCourierWithoutRequiredFieldsTest(CourierCredentials courierCredentials, int expStatusCode, String expErrorMessage) {
        this.courierCredentials = courierCredentials;
        this.expStatusCode = expStatusCode;
        this.expErrorMessage = expErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getLoginCourierData() {
        return new Object[][] {
                {CourierCredentials.getWithLoginOnly(COURIER), 400, "Недостаточно данных для входа"},
                {CourierCredentials.getWithPasswordOnly(COURIER), 400, "Недостаточно данных для входа"},
                {CourierCredentials.getWithFakeLoginAndPassword(), 404, "Учетная запись не найдена"},
        };
    }

    @Test
    @DisplayName("Login courier without login or password")
    public void loginCourierWithoutRequiredFieldsTest(){

        COURIER_CLIENT.create(COURIER);
        ValidatableResponse response = COURIER_CLIENT.login(CourierCredentials.from(COURIER));
        courierId = response.extract().path("id");
        ValidatableResponse errorResponse = new CourierClient().login(courierCredentials);
        int statusCode = errorResponse.extract().statusCode();
        assertThat("Некорректный код статуса", statusCode, equalTo(expStatusCode));

        String errorMessage = errorResponse.extract().path("message");
        assertEquals ("Некорректное сообщение об ошибке", expErrorMessage, errorMessage);

        //в проверке с авторизацией только с логином баг - отваливается по таймауту:
//            //Expected :400
//            //Actual   :504

    }

}




