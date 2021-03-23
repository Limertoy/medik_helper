package sample;

<<<<<<< HEAD:src/sample/ControllerWizyty.java
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.awt.*;

import javafx.stage.Stage;

public class ControllerWizyty {
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
=======
public class Controller_wizyty {
>>>>>>> parent of 300a13f (Poprawa "wizyty"):src/sample/Controller_wizyty.java
}
