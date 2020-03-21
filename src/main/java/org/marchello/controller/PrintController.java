package org.marchello.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.marchello.model.Person;
import org.marchello.model.Svc;
import org.marchello.service.DocService;
import org.marchello.service.PersonService;
import org.marchello.service.SvcService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrintController {
    public TextArea errorMessage;
    public Button exitButton;
    public RadioButton radioButton1;
    public RadioButton radioButton2;
    public ListView<String> personList;
    public ListView<String> serviceList;
    public TextField searchInputPerson;
    public Button printButton;

    private ToggleGroup group = new ToggleGroup();
    private RadioButton selectedButton;

    @FXML
    public void initialize() throws IOException {
        this.radioButton1.setToggleGroup(this.group);
        this.radioButton2.setToggleGroup(this.group);
        this.group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedButton = (RadioButton) newValue;
            }
        });
        showPersonList(PersonService.getAllPersons());
        showServiceList(SvcService.getAllServices());
    }

    @FXML
    public void clickButtonExit() {
        ((Stage) this.exitButton.getScene().getWindow()).close();
    }

    @FXML
    public void clickButtonSearchPerson() throws FileNotFoundException {
        List<Person> searchPersons = PersonService.searchPersonsByFIO(this.searchInputPerson.getText());
        if (searchPersons.isEmpty())
            this.errorMessage.setText("Поиск по запросу \"" + this.searchInputPerson.getText() + "\"\nникого не нашел!\nПопробуйте еще раз.");
        showPersonList(searchPersons);
    }

    @FXML
    public void clickButtonPrint() throws IOException {
        List<Svc> selectedServices = new ArrayList<>();
        Person selectedPerson = null;
        for (String item : this.serviceList.getSelectionModel().getSelectedItems())
            selectedServices.add(SvcService.getServiceById(item.substring(1, item.indexOf(")"))));
        if (this.personList.getSelectionModel().getSelectedItem() != null)
            selectedPerson = PersonService.getPersonById(this.personList.getSelectionModel().getSelectedItem()
                .substring(1, this.personList.getSelectionModel().getSelectedItem().indexOf(")")));
        if (this.selectedButton != null && selectedPerson != null && !selectedServices.isEmpty()) {
            boolean flag = false;
            if (this.selectedButton.getText().equals("Бадыгина Н.A.")) flag = true;
            DocService.createDoc(selectedPerson, flag, selectedServices);
        }
    }

    private void showPersonList(List<Person> persons) {
        ObservableList<String> data = FXCollections.observableArrayList();
        for (Person person : persons)
            data.add("(" + person.getId() + ") " + person.getSurname() + " " + person.getName() + " " + person.getPatronymic());
        this.personList.setItems(data);
    }

    private void showServiceList(List<Svc> svcs) {
        ObservableList<String> data = FXCollections.observableArrayList();
        for (Svc svc : svcs)
            data.add("(" + svc.getId() + ") " + svc.getName());
        this.serviceList.setItems(data);
        this.serviceList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}