package sample;

import com.sun.glass.ui.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.awt.*;
import java.io.IOException;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerLogin {
    public Stage primaryStage;
    public Scene wizytyScene;
    @FXML
    public Button buttonLogin, exit_button, minimalize_button;

    @FXML
    public AnchorPane dynamicPane;

    public ControllerLogin(){}

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.setIconified(true);
    }

    private void setDynamicPane(AnchorPane dynamicPane){
        this.dynamicPane.getChildren().clear();
        this.dynamicPane.getChildren().add(dynamicPane);
    }

    public void setWizytyScene(Scene scene) {
        wizytyScene = scene;
    }

    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wizyty(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(wizytyScene);
    }

}
