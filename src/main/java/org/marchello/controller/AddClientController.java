package org.marchello.controller;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.codehaus.plexus.util.StringUtils;
import org.marchello.model.Person;
import org.marchello.service.PersonService;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
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
    public Button exitButton;

    @FXML
    public void clickButtonExit() {
        ((Stage) this.exitButton.getScene().getWindow()).close();
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
        this.errorMessage.setText("Форма очищена!");
    }

    @FXML
    public void clickButtonAdd() throws IOException {
        String error = formErrorMessage();
        this.errorMessage.setText(error);
        if (error.equals("")) {
            Person person = new Person(this.surname.getText(),
                    this.name.getText(),
                    this.patronymic.getText(),
                    this.phone.getText(),
                    this.address.getText(),
                    this.seriesPassport.getText().toUpperCase(),
                    this.numberPassport.getText(),
                    this.idNumberPassport.getText().toUpperCase(),
                    this.whenIssuedPassport.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    this.whoIssuedPassport.getText());
            PersonService.addPerson(person);
            clickButtonClear();
            this.errorMessage.setText("Ошибок не найдено.\nКлиент добавлен!");
        }
    }

    private String formErrorMessage() {
        String message = "";
        if (this.surname.getText().equals(""))
            message += "Не указана фамилия!\n";
        else if (!StringUtils.isAlpha(this.surname.getText()))
            message += "В полe 'Фамилия' можно\nвводить только буквы!\n";
        if (this.name.getText().equals(""))
            message += "Не указано имя!\n";
        else if (!StringUtils.isAlpha(this.name.getText()))
            message += "В полe 'Имя' можно\nвводить только буквы!\n";
        if (this.patronymic.getText().equals(""))
            message += "Не указано отчество!\n";
        else if (!StringUtils.isAlpha(this.patronymic.getText()))
            message += "В полe 'Отчество' можно\nвводить только буквы!\n";
        if (this.seriesPassport.getText().length() != 2)
            message += "Серия паспорта должна содержать\n2 буквы!\n";
        else if (!StringUtils.isAlpha(this.seriesPassport.getText()))
            message += "В полe 'Серия' можно\nвводить только буквы!\n";
        if (this.numberPassport.getText().length() != 7)
            message += "Номер паспорта должен содержать\n7 цифр!\n";
        else if (!StringUtils.isNumeric(this.numberPassport.getText()))
            message += "В полe 'Номер' можно\nвводить только цифры!\n";
        if (this.idNumberPassport.getText().length() != 14)
            message += "Идент. номер должен содержать\n14 символов!\n";
        else if (!StringUtils.isAlphanumeric(this.idNumberPassport.getText()))
            message += "В полe 'Идент. номер' можно\nвводить только буквы и цифры!\n";
        if (this.whenIssuedPassport.getValue() == null)
            message += "Поле 'Дата выдачи' не заполнено!\n";
        if (this.whoIssuedPassport.getText().equals(""))
            message += "Поле 'Кем выдан' не заполнено!";
        else if (!StringUtils.isAlphaSpace(this.whoIssuedPassport.getText()))
            message += "В полe 'Кем выдан' можно\nвводить только буквы!\n";
        return message;
    }
}