package com.marchello.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class SearchClientController {
    public TextArea errorMessage;
    public ListView listPerson;
    public TextField searchInput;
    public Label fio;
    public Label phone;
    public Label address;
    public Label seriesAndNumberPassport;
    public Label idNumberPassport;
    public Label whenIssuedPassport;
    public Label whoIssuedPassport;

    @FXML
    public void close(){
        Platform.exit();
    }

    @FXML
    public void initialize() {}
}
