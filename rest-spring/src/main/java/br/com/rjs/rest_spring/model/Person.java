package br.com.rjs.rest_spring.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "TB_name")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String  name;
    @Column(length = 10)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false, length = 6)
    private Character gender;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthday) {
        this.birthDay = birthday;
    }

    public Person(long id, String name, Date birthday, String address, Character gender){
        this.id = id;
        this.name = name;
        this.birthDay = birthday;
        this.address = address;
        this.gender = gender;
    }

    public Person(){}

}
