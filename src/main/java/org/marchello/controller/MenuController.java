package org.marchello.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.marchello.Main;
import java.io.IOException;

public class MenuController {
    private static final Stage STAGE_ADD_CLIENT = new Stage();
    private static final Stage STAGE_EDIT_CLIENT = new Stage();
    private static final Stage STAGE_ADD_SERVICE = new Stage();
    private static final Stage STAGE_EDIT_SERVICE = new Stage();
    private static final Stage STAGE_PRINT = new Stage();

    @FXML
    public void clickButtonExit() {
        Platform.exit();
    }

    @FXML
    public void clickButtonAddClient() throws IOException {
        Main.openWindow(STAGE_ADD_CLIENT, "/view/addClient.fxml");
    }

    @FXML
    public void clickButtonEditClient() throws IOException {
        Main.openWindow(STAGE_EDIT_CLIENT, "/view/editClient.fxml");
    }

    @FXML
    public void clickButtonAddSvc() throws IOException {
        Main.openWindow(STAGE_ADD_SERVICE, "/view/addSvc.fxml");
    }

    @FXML
    public void clickButtonEditService() throws IOException {
        Main.openWindow(STAGE_EDIT_SERVICE, "/view/editSvc.fxml");
    }

    public void clickButtonPrint() throws IOException {
        Main.openWindow(STAGE_PRINT, "/view/print.fxml");
    }
}
