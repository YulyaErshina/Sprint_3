package ru.yandex.praktikum.scooter.api.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierCredentials {

    public String login;
    public String password;


    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierCredentials(){

    }

    public String getLogin() {
        return login;
    }

    public CourierCredentials setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CourierCredentials setPassword(String password) {
        this.password = password;
        return this;
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier.login, courier.password);
    }

    public static CourierCredentials getWithLoginOnly(Courier courier) {
        return new CourierCredentials().setLogin(courier.login);
    }

    public static CourierCredentials getWithPasswordOnly(Courier courier) {
        return new CourierCredentials().setPassword(courier.password);
    }

    public static CourierCredentials getWithFakeLoginAndPassword() {
        return new CourierCredentials().setLogin(RandomStringUtils.randomAlphabetic(5))
                .setPassword(RandomStringUtils.randomAlphabetic(4));
    }

    @Override
    public String toString() {
        return "CourierCredentials {login:"+login+",password:"+password;
    }
}

