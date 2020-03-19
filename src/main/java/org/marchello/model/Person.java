package org.marchello.model;

import org.marchello.service.PersonService;
import java.io.IOException;

public class Person{
    private String id;
    private String surname;
    private String name;
    private String patronymic;
    private String phone;
    private String address;
    private String seriesPassport;
    private String numberPassport;
    private String idNumberPassport;
    private String whenIssuedPassport;
    private String whoIssuedPassport;

    public Person(String surname,
                  String name,
                  String patronymic,
                  String phone,
                  String address,
                  String seriesPassport,
                  String numberPassport,
                  String idNumberPassport,
                  String whenIssuedPassport,
                  String whoIssuedPassport) throws IOException {
        this.id = PersonService.readIdFromFile();
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone = phone;
        this.address = address;
        this.seriesPassport = seriesPassport;
        this.numberPassport = numberPassport;
        this.idNumberPassport = idNumberPassport;
        this.whenIssuedPassport = whenIssuedPassport;
        this.whoIssuedPassport = whoIssuedPassport;
    }

    public Person() {}

    //GETTERS AND SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSeriesPassport() {
        return seriesPassport;
    }

    public void setSeriesPassport(String seriesPassport) {
        this.seriesPassport = seriesPassport;
    }

    public String getNumberPassport() {
        return numberPassport;
    }

    public void setNumberPassport(String numberPassport) {
        this.numberPassport = numberPassport;
    }

    public String getIdNumberPassport() {
        return idNumberPassport;
    }

    public void setIdNumberPassport(String idNumberPassport) {
        this.idNumberPassport = idNumberPassport;
    }

    public String getWhenIssuedPassport() {
        return whenIssuedPassport;
    }

    public void setWhenIssuedPassport(String whenIssuedPassport) {
        this.whenIssuedPassport = whenIssuedPassport;
    }

    public String getWhoIssuedPassport() {
        return whoIssuedPassport;
    }

    public void setWhoIssuedPassport(String whoIssuedPassport) {
        this.whoIssuedPassport = whoIssuedPassport;
    }
}
