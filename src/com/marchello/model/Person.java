package com.marchello.model;

import java.io.Serializable;

public class Person implements Serializable {

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

    public Person(String surname, String name, String patronymic, String phone, String address, String seriesPassport, String numberPassport, String idNumberPassport, String whenIssuedPassport, String whoIssuedPassport) {
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

    @Override
    public String toString() {
        return "Person {name = " + name + ", surname = " + surname + ", patronymic = " + patronymic +
                ", phone = " + phone + ", address = " + address + ", seriesPassport = " + seriesPassport +
                ", numberPassport = " + numberPassport + ", idNumberPassport = " + idNumberPassport +
                ", whenIssuedPassport = " + whenIssuedPassport + ", whoIssuedPassport = " + whoIssuedPassport + "}";
    }
}
