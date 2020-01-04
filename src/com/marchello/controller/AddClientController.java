package com.marchello.controller;

import com.marchello.model.Person;
import com.marchello.service.PersonService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;
import java.time.format.DateTimeFormatter;


public class AddClientController {
    public TextField surname;
    public TextField name;
    public TextField patronymic;
    public TextField phone;
    public TextField address;
    public TextField seriesPassport;
    public TextField numberPassport;
    public TextField idNumberPassport;
    public DatePicker whenIssuedPassport;
    public TextField whoIssuedPassport;
    public TextArea errorMessage;

    @FXML
    public void clickButtonExit() {
        Platform.exit();
    }

    @FXML
    public void clickButtonClear() {
        this.surname.setText("");
        this.name.setText("");
        this.patronymic.setText("");
        this.phone.setText("");
        this.address.setText("");
        this.seriesPassport.setText("");
        this.numberPassport.setText("");
        this.idNumberPassport.setText("");
        this.whenIssuedPassport.setValue(null);
        this.whoIssuedPassport.setText("");
        this.errorMessage.setText("");
    }

    @FXML
    public void clickButtonAdd() throws IOException {
        String error = formErrorMessage();
        this.errorMessage.setText(error);
        if(error.equals("")){
            Person person = new Person(surname.getText(),
                    name.getText(),
                    patronymic.getText(),
                    phone.getText(),
                    address.getText(),
                    seriesPassport.getText(),
                    numberPassport.getText(),
                    idNumberPassport.getText(),
                    whenIssuedPassport.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    whoIssuedPassport.getText());
            PersonService personService = new PersonService();
            personService.addPerson(person);
            clickButtonClear();
            this.errorMessage.setText("Ошибок не найдено.\nКлиент добавлен!");
        }
    }

    private String formErrorMessage() {
        String message = "";
        if(this.surname.getText().equals("")) message += "Не указана фамилия!\n";
        if(this.name.getText().equals("")) message += "Не указано имя!\n";
        if(this.patronymic.getText().equals("")) message += "Не указано отчество!\n";
        if(this.seriesPassport.getText().length() != 2) message += "Серия паспорта должна содержать 2 буквы!\n";
        if(this.numberPassport.getText().length() != 7) message += "Номер паспорта должен содержать 7 цифр!\n";
        if(this.idNumberPassport.getText().length() != 14) message += "Идент. номер должен содержать 14 символов!\n";
        if(this.whenIssuedPassport.getValue() == null) message += "Поле 'Дата выдачи' не заполнено!\n";
        if(this.whoIssuedPassport.getText().equals("")) message += "Поле 'Кем выдан' не заполнено!";
        return message;
    }
}