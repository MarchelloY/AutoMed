package com.marchello.controller;

import com.marchello.model.Person;
import com.marchello.service.PersonService;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class SearchClientController {
    public TextArea errorMessage;
    public TextField searchInput;
    public ListView personList;
    public Label fio;
    public Label phone;
    public Label address;
    public Label seriesAndNumberPassport;
    public Label idNumberPassport;
    public Label whenIssuedPassport;
    public Label whoIssuedPassport;

    private PersonService personService = new PersonService();

    @FXML
    public void clickButtonExit() {
        Platform.exit();
    }

    @FXML
    public void clickButtonSearch() throws FileNotFoundException {
        errorMessage.setText("");
        List<Person> searchPersons = this.personService.searchPersonsByFIO(searchInput.getText());
        if(searchPersons.isEmpty()) {
            errorMessage.setText("Поиск по запросу \"" + searchInput.getText() + "\" ничего не нашел!\nПопробуйте еще раз.");
        }
        showPersonList(searchPersons);
    }

    @FXML
    public void initialize() throws IOException {
        showPersonList(this.personService.getAllPersons());
    }

    public void showPersonList(List<Person> persons) {
        ObservableList<String> data = FXCollections.observableArrayList();
        for(Person person : persons)
            data.add("(" + person.getId() + ") " + person.getSurname() + " " + person.getName() + " " + person.getPatronymic());
        this.personList.setItems(data);
        this.personList.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            Person selectedPerson = null;
            try {
                selectedPerson = this.personService.getPersonById(newValue.substring(1, newValue.lastIndexOf(")")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (selectedPerson != null) {
                this.fio.setText(selectedPerson.getSurname() + " " + selectedPerson.getName() + " " + selectedPerson.getPatronymic());
                this.phone.setText(selectedPerson.getPhone());
                this.address.setText(selectedPerson.getAddress());
                this.seriesAndNumberPassport.setText(selectedPerson.getSeriesPassport() + selectedPerson.getNumberPassport());
                this.idNumberPassport.setText(selectedPerson.getIdNumberPassport());
                this.whenIssuedPassport.setText(selectedPerson.getWhenIssuedPassport());
                this.whoIssuedPassport.setText(selectedPerson.getWhoIssuedPassport());
            }
        });
    }
}
