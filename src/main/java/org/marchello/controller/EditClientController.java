package org.marchello.controller;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.marchello.model.Person;
import org.marchello.service.PersonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class EditClientController {
    public TextArea errorMessage;
    public TextField searchInput;
    public ListView<String> personList;
    public Button exitButton;
    public TextField surname;
    public TextField name;
    public TextField patronymic;
    public TextField phone;
    public TextField address;
    public TextField seriesPassport;
    public TextField numberPassport;
    public TextField idNumberPassport;
    public TextField whenIssuedPassport;
    public TextField whoIssuedPassport;
    public Button updateButton;
    public Button deleteButton;
    private Person selectedPerson;

    @FXML
    public void clickButtonExit() {
        ((Stage) this.exitButton.getScene().getWindow()).close();
    }

    @FXML
    public void clickButtonSearch() throws FileNotFoundException {
        List<Person> searchPersons = PersonService.searchPersonsByFIO(searchInput.getText());
        clearForm();
        if(searchPersons.isEmpty())
            errorMessage.setText("Поиск по запросу \"" + searchInput.getText() + "\"\nникого не нашел!\nПопробуйте еще раз.");
        disableForm(true);
        showPersonList(searchPersons);
    }

    @FXML
    public void initialize() throws IOException {
        disableForm(true);
        showPersonList(PersonService.getAllPersons());
    }

    @FXML
    public void clickButtonDelete() throws IOException {
        if(this.selectedPerson != null) {
            PersonService.deletePersonById(this.selectedPerson.getId());
            clickButtonSearch();
        }
    }

    @FXML
    public void clickButtonEdit() throws IOException {
        String error = formErrorMessage();
        this.errorMessage.setText(error);
        if (this.selectedPerson != null && error.equals("")) {
            Person newPerson = this.selectedPerson;
            newPerson.setSurname(this.surname.getText());
            newPerson.setName(this.name.getText());
            newPerson.setPatronymic(this.patronymic.getText());
            newPerson.setPhone(this.phone.getText());
            newPerson.setAddress(this.address.getText());
            newPerson.setSeriesPassport(this.seriesPassport.getText());
            newPerson.setNumberPassport(this.numberPassport.getText());
            newPerson.setIdNumberPassport(this.idNumberPassport.getText());
            newPerson.setWhenIssuedPassport(this.whenIssuedPassport.getText());
            newPerson.setWhoIssuedPassport(this.whoIssuedPassport.getText());
            PersonService.updatePerson(newPerson);
            clickButtonSearch();
        }
    }

    private void showPersonList(List<Person> persons) {
        ObservableList<String> data = FXCollections.observableArrayList();
        for(Person person : persons)
            data.add("(" + person.getId() + ") " + person.getSurname() + " " + person.getName() + " " + person.getPatronymic());
        this.personList.setItems(data);
        this.personList.getSelectionModel().selectedItemProperty().addListener(this::clickItemPerson);
    }

    private void clearForm(){
        this.name.setText("");
        this.surname.setText("");
        this.patronymic.setText("");
        this.phone.setText("");
        this.address.setText("");
        this.seriesPassport.setText("");
        this.numberPassport.setText("");
        this.idNumberPassport.setText("");
        this.whenIssuedPassport.setText("");
        this.whoIssuedPassport.setText("");
        this.errorMessage.setText("");
    }

    private void clickItemPerson(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        this.selectedPerson = null;
        if (newValue != null)
            try {
                this.selectedPerson = PersonService.getPersonById(newValue.substring(1, newValue.lastIndexOf(")")));
                disableForm(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        if (this.selectedPerson != null) {
            this.surname.setText(this.selectedPerson.getSurname());
            this.name.setText(this.selectedPerson.getName());
            this.patronymic.setText(this.selectedPerson.getPatronymic());
            this.phone.setText(this.selectedPerson.getPhone());
            this.address.setText(this.selectedPerson.getAddress());
            this.seriesPassport.setText(this.selectedPerson.getSeriesPassport());
            this.numberPassport.setText(this.selectedPerson.getNumberPassport());
            this.idNumberPassport.setText(this.selectedPerson.getIdNumberPassport());
            this.whenIssuedPassport.setText(this.selectedPerson.getWhenIssuedPassport());
            this.whoIssuedPassport.setText(this.selectedPerson.getWhoIssuedPassport());
        }
    }

    private void disableForm(boolean flag) {
        this.updateButton.setDisable(flag);
        this.deleteButton.setDisable(flag);
        this.surname.setDisable(flag);
        this.name.setDisable(flag);
        this.patronymic.setDisable(flag);
        this.phone.setDisable(flag);
        this.address.setDisable(flag);
        this.seriesPassport.setDisable(flag);
        this.numberPassport.setDisable(flag);
        this.idNumberPassport.setDisable(flag);
        this.whenIssuedPassport.setDisable(flag);
        this.whoIssuedPassport.setDisable(flag);
    }

    private String formErrorMessage() {
        String message = "";
        if(this.surname.getText().equals("")) message += "Не указана фамилия!\n";
        if(this.name.getText().equals("")) message += "Не указано имя!\n";
        if(this.patronymic.getText().equals("")) message += "Не указано отчество!\n";
        if(this.seriesPassport.getText().length() != 2) message += "Серия паспорта должна содержать\n2 буквы!\n";
        if(this.numberPassport.getText().length() != 7) message += "Номер паспорта должен содержать\n7 цифр!\n";
        if(this.idNumberPassport.getText().length() != 14) message += "Идент. номер должен содержать\n14 символов!\n";
        if(this.whenIssuedPassport.getText().equals("")) message += "Поле 'Дата выдачи' не заполнено!\n";
        if(this.whoIssuedPassport.getText().equals("")) message += "Поле 'Кем выдан' не заполнено!";
        return message;
    }
}



//ПОТОМ К ПЕЧАТИ

//public RadioButton radioButton1;
//public RadioButton radioButton2;
//private ToggleGroup group = new ToggleGroup();

//    if (!flag) this.group.selectToggle(this.radioButton1);
//    else this.group.selectToggle(null);

//this.radioButton1.setToggleGroup(this.group);
//this.radioButton2.setToggleGroup(this.group);
//this.group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
//    if (newValue != null) {
//        RadioButton selectedButton = (RadioButton) newValue;
//        System.out.println(selectedButton.getText());
//    }
//});