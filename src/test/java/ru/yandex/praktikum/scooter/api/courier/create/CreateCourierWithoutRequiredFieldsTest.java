package ru.yandex.praktikum.scooter.api.courier.create;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.scooter.api.courier.Courier;
import ru.yandex.praktikum.scooter.api.courier.CourierClient;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CreateCourierWithoutRequiredFieldsTest {

    private CourierClient courierClient;
    Courier courier;
    private final int expStatusCode;
    private final String expErrorMessage;

    public CreateCourierWithoutRequiredFieldsTest(Courier courier, int expStatusCode, String expErrorMessage) {
        this.courier = courier;
        this.expStatusCode = expStatusCode;
        this.expErrorMessage = expErrorMessage;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2}")
    public static Object[][] getCourierData() {
        return new Object[][]{
                {Courier.getWithLoginOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithPasswordOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getCourierWithFirstNameOnly(), 400, "Недостаточно данных для создания учетной записи"}
        };
    }

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Creation of a courier without login and password")
    @Description("The user can't create a new courier if the login field or the password field is not filled")
    public void createCourierWithoutRequiredFields() {

        ValidatableResponse response = courierClient.create(courier);
        int statusCode = response.extract().statusCode();
        String errorMessage = response.extract().path("message");
        assertEquals("Некорректный код статуса", expStatusCode, statusCode);
        assertEquals("Некорректное сообщение об ошибке", expErrorMessage, errorMessage);
    }
}

