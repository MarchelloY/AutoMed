package org.marchello;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.marchello.model.Person;
import org.marchello.service.DocService;
import org.marchello.service.PersonService;

import java.io.IOException;

public class Main extends Application {

    private static double xOffset;
    private static double yOffset;

    public static void openWindow(Stage stage, String path) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(path));
        Scene scene = new Scene(root);
        scene.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });
        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.centerOnScreen();
        if(stage.getStyle() != StageStyle.UNDECORATED)
            stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        //Person person = PersonService.getPersonById("1");
        //DocService.createDoc(person, false);
        openWindow(stage, "/view/menu.fxml");
    }
}
