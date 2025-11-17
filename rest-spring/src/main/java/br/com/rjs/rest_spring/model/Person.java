package br.com.rjs.rest_spring.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Person {

    private String  name;
    private String address;
    private Character gender;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person(int id, String name, String address, Character gender){
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
    }


}
