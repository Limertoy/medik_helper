package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.awt.*;
import java.awt.event.ActionEvent;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Button buttonLogin;

    @FXML
    private Button exit_button;

    @FXML
    private Button minimalize_button;

    Stage primaryStage;

    @FXML
    void exit(ActionEvent event) {
        primaryStage.close();
    }


}
