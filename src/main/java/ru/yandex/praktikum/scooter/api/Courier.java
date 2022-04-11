package ru.yandex.praktikum.scooter.api;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {

    public String login;
    public String password;
    public String firstName;

    public Courier(String login, String password, String firstName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public Courier(){

    }

    public String getLogin() {
        return login;
    }

    public Courier setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Courier setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Courier setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public static Courier getRandomCourier() {
        String login = RandomStringUtils.randomAlphabetic(5);
        String password = RandomStringUtils.randomAlphabetic(4);
        String firstName = RandomStringUtils.randomAlphabetic(5);
        return new Courier(login, password, firstName);
    }

    public static Courier getWithLoginOnly() {
        return new Courier().setLogin(RandomStringUtils.randomAlphabetic(5));
    }

    public static Courier getWithPasswordOnly() {
        return new Courier().setPassword(RandomStringUtils.randomAlphabetic(4));
    }

    public static Courier getCourierWithFirstNameOnly(){
        return new Courier().setFirstName(RandomStringUtils.randomAlphabetic(5));
    }

    @Override
    public String toString() {
        return "Courier {login:"+login+",password:"+password+",firstName:"+firstName+"}";
    }



}
