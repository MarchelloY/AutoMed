package org.marchello.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.codehaus.plexus.util.StringUtils;
import org.marchello.model.Svc;
import org.marchello.service.SvcService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class EditSvcController {
    public TextArea errorMessage;
    public TextField searchInput;
    public ListView<String> serviceList;
    public Button exitButton;
    public TextField name;
    public TextField costr;
    public TextField costk;
    public Button updateButton;
    public Button deleteButton;

    private Svc selectedService;

    @FXML
    public void clickButtonExit() {
        ((Stage) this.exitButton.getScene().getWindow()).close();
    }

    @FXML
    public void clickButtonSearch() throws FileNotFoundException {
        List<Svc> searchServices = SvcService.searchServicesByName(this.searchInput.getText());
        clearForm();
        if (searchServices.isEmpty())
            this.errorMessage.setText("Поиск по запросу \"" + this.searchInput.getText() + "\"\nникого не нашел!\nПопробуйте еще раз.");
        disableForm(true);
        showServiceList(searchServices);
    }

    @FXML
    public void initialize() throws IOException {
        disableForm(true);
        showServiceList(SvcService.getAllServices());
    }

    @FXML
    public void clickButtonDelete() throws IOException {
        if(this.selectedService != null) {
            SvcService.deleteServiceById(this.selectedService.getId());
            clickButtonSearch();
        }
    }

    @FXML
    public void clickButtonEdit() throws IOException {
        String error = formErrorMessage();
        this.errorMessage.setText(error);
        if (error.equals("") && this.selectedService != null) {
            Svc newService = this.selectedService;
            newService.setName(this.name.getText());
            newService.setCost(this.costr.getText() + "," + this.costk.getText());
            SvcService.updateService(newService);
            clickButtonSearch();
        }
    }

    private void showServiceList(List<Svc> svcs) {
        ObservableList<String> data = FXCollections.observableArrayList();
        for (Svc svc : svcs)
            data.add("(" + svc.getId() + ") " + svc.getName());
        this.serviceList.setItems(data);
        this.serviceList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.selectedService = null;
            if (newValue != null)
                try {
                    this.selectedService = SvcService.getServiceById(newValue.substring(1, newValue.indexOf(")")));
                    disableForm(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (this.selectedService != null) {
                this.name.setText(this.selectedService.getName());
                this.costr.setText(this.selectedService.getCost().substring(0, (this.selectedService.getCost().indexOf(","))));
                this.costk.setText(this.selectedService.getCost().substring(this.selectedService.getCost().indexOf(",") + 1));
            }
        });
    }

    private void clearForm(){
        this.name.setText("");
        this.costr.setText("");
        this.costk.setText("");
        this.errorMessage.setText("");
    }

    private void disableForm(boolean flag) {
        this.updateButton.setDisable(flag);
        this.deleteButton.setDisable(flag);
        this.name.setDisable(flag);
        this.costr.setDisable(flag);
        this.costk.setDisable(flag);
    }

    private String formErrorMessage() {
        String message = "";
        if (this.name.getText().equals("")) message += "Не указано название!\n";
        if (this.costk.getText().equals("") && this.costr.getText().equals(""))
            message += "Не указана стоимость!\n";
        else if (!StringUtils.isNumeric(this.costr.getText()) || !StringUtils.isNumeric(this.costk.getText()))
            message += "В поля 'Рубли' и 'Копейки'\nможно вводить только числа!\n";
        return message;
    }
}
