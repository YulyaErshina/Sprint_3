package ru.yandex.praktikum.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.scooter.api.ColorOrder;
import ru.yandex.praktikum.scooter.api.Order;
import ru.yandex.praktikum.scooter.api.OrderClient;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private OrderClient orderClient;
    private Order order;
    private final int expStatusCode;
    private final List<ColorOrder> color;
    private int track;

    public CreateOrderTest(List<ColorOrder> color, int expStatusCode){
        this.color = color;
        this.expStatusCode = expStatusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getColorData() {
        return new Object[][] {
                {List.of(ColorOrder.BLACK), 201},
                {List.of(ColorOrder.GREY), 201},
                {null, 201},
                {List.of(ColorOrder.BLACK, ColorOrder.GREY), 201},
        };
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
        order = Order.getRandomOrder().setColor(color);
    }

    @After
    public void tearDown() {
        orderClient.cancel(track);
    }

    @Test
    @DisplayName("Create an order when the user fills in or doesn't fill in the color field")
    public void createOrderWithOrWithoutFillColor(){

        ValidatableResponse response = orderClient.createNewOrder(order);

        int statusCode = response.extract().statusCode();
        track = response.extract().path("track");

        assertEquals("Некорректный код статуса", expStatusCode, statusCode);
        assertThat("Некорректный ID трека", track, notNullValue());

    }

}

