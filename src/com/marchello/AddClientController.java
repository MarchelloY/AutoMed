package com.marchello;

import com.alibaba.fastjson.JSON;
import com.marchello.model.Person;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    public void close(){
        Platform.exit();
    }

    @FXML
    public void clear() {
        surname.setText("");
        name.setText("");
        patronymic.setText("");
        phone.setText("");
        address.setText("");
        seriesPassport.setText("");
        numberPassport.setText("");
        idNumberPassport.setText("");
        whenIssuedPassport.setValue(null);
        whoIssuedPassport.setText("");
        errorMessage.setText("");
    }

    public void formPerson() throws IOException {
        String error = formErrorMessage();
        errorMessage.setText(error);
        if(error.equals("")){
            Person person = new Person(
                    surname.getText(),
                    name.getText(),
                    patronymic.getText(),
                    phone.getText(),
                    address.getText(),
                    seriesPassport.getText(),
                    numberPassport.getText(),
                    idNumberPassport.getText(),
                    whenIssuedPassport.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    whoIssuedPassport.getText()
            );
            addPersonInFile(person);
            clear();
            errorMessage.setText("Ошибок не найдено.\nКлиент добавлен!");
        }
    }

    public void addPersonInFile (Person person) throws IOException {
        File file = new File("persons.json");
        Scanner scanner = new Scanner(file);
        List<Person> persons;
        if(file.length() == 0) persons = new ArrayList<>();
        else persons = JSON.parseArray(scanner.nextLine(), Person.class);
        persons.add(person);
        String jsonString = JSON.toJSONString(persons);
        FileWriter fw = new FileWriter(file);
        fw.write(jsonString);
        fw.close();
    }

    public String formErrorMessage() {
        String message = "";
        if(surname.getText().equals("")) message += "Не указана фамилия!\n";
        if(name.getText().equals("")) message += "Не указано имя!\n";
        if(patronymic.getText().equals("")) message += "Не указано отчество!\n";
        if(seriesPassport.getText().length() != 2) message += "Серия паспорта должна содержать 2 буквы!\n";
        if(numberPassport.getText().length() != 7) message += "Номер паспорта должен содержать 7 цифр!\n";
        if(idNumberPassport.getText().length() != 14) message += "Идент. номер должен содержать 14 символов!\n";
        if(whenIssuedPassport.getValue() == null) message += "Поле 'Дата выдачи' не заполнено!\n";
        if(whoIssuedPassport.getText().equals("")) message += "Поле 'Кем выдан' не заполнено!";
        return message;
    }
}