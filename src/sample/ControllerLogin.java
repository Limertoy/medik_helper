package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.awt.*;

import javafx.stage.Stage;

public class ControllerLogin {
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void exit(ActionEvent actionEvent) {
        primaryStage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        primaryStage.setIconified(true);
    }


}
