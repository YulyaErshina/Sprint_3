package ru.yandex.praktikum.scooter.api.order;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class Order {
    public String firstname;
    public String lastname;
    public String address;
    public int metroStation;
    public String phone;
    public int rentTime;
    public String deliveryDate;
    public String comment;
    public List<ColorOrder> color;

    public Order(String firstname, String lastname, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<ColorOrder> color) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public Order() {

    }

    public Order setColor(List<ColorOrder> color) {
        this.color = color;
        return this;
    }

    public static Order getRandomOrder() {
        Faker faker = new Faker(new Locale("ru"));

        String orderFirstname = faker.name().firstName();
        String orderLastname = faker.name().lastName();
        String orderAddress = faker.address().streetAddress();
        int orderMetroStation = faker.number().numberBetween(1, 101);
        String orderPhone = faker.phoneNumber().phoneNumber();
        int orderRentTime = faker.number().numberBetween(1, 366);
        Date date = faker.date().future(5, TimeUnit.DAYS);
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        String orderDeliveryDate = dt1.format(date);
        String orderComment = faker.address().streetAddress();
        List<ColorOrder> orderColor = new ArrayList<>();
        return new Order(orderFirstname, orderLastname, orderAddress, orderMetroStation, orderPhone,
                orderRentTime, orderDeliveryDate, orderComment, orderColor);


    }

    @Override
    public String toString() {
        return "firstName:" + firstname + " ,lastName:" + lastname + " ,address:" + address + " ,metroStation:" + metroStation + " ,phone:" + phone + " ,rentTime:" + rentTime + " ,deliveryDate:" + deliveryDate + " ,comment:" + comment + " ,color:" + "[" + color + "]}";
    }

}



