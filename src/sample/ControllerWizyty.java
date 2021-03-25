package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.awt.*;
import java.io.IOException;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControllerWizyty {
    Stage primaryStage;
    Scene loginScene;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;
    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> nazwisko_table,imie_table,pesel_table,data_table,godzina_table;

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

    public void setLoginScene(Scene scene) {
        loginScene = scene;
    }

    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(loginScene);
    }
}
