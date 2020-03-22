package org.marchello.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.codehaus.plexus.util.StringUtils;
import org.marchello.model.Svc;
import org.marchello.service.SvcService;
import java.io.IOException;

public class AddSvcController {
    public TextField name;
    public TextField costr;
    public TextField costk;
    public Button exitButton;
    public TextArea errorMessage;

    @FXML
    public void clickButtonExit() {
        ((Stage) this.exitButton.getScene().getWindow()).close();
    }

    @FXML
    public void clickButtonAdd() throws IOException {
        String error = formErrorMessage();
        this.errorMessage.setText(error);
        if (error.equals("")){
            StringBuilder result = new StringBuilder();
            if (this.costr.getText().equals("")) result.append("0"); else result.append(this.costr.getText());
            result.append(",");
            if (this.costk.getText().equals("")) result.append("0"); else result.append(this.costk.getText());
            Svc service = new Svc(this.name.getText(), result.toString());
            SvcService.addService(service);
            clickButtonClear();
            this.errorMessage.setText("Ошибок не найдено.\nУслуга добавлена!");
        }
    }

    @FXML
    public void clickButtonClear() {
        this.name.setText("");
        this.costr.setText("");
        this.costk.setText("");
        this.errorMessage.setText("Форма очищена!");
    }

    private String formErrorMessage() {
        String message = "";
        if (this.name.getText().equals("")) message += "Не указано название!\n";
        if (this.costk.getText().equals("") && this.costr.getText().equals(""))
            message += "Не указана стоимость!\n";
        else if (!StringUtils.isNumeric(this.costr.getText()) || !StringUtils.isNumeric(this.costk.getText()))
            message += "В полe 'Стоимость' можно\nвводить только числа!\n";
        return message;
    }
}
