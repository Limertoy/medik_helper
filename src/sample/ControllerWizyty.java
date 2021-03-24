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

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> nazwisko_table;

    @FXML
    private TableColumn<?, ?> imie_table;

    @FXML
    private TableColumn<?, ?> pesel_table;

    @FXML
    private TableColumn<?, ?> data_table;

    @FXML
    private TableColumn<?, ?> godzina_table;

    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void back(ActionEvent actionEvent) throws IOException {
        Parent wizytyParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene wizytyScene = new Scene(wizytyParent);

        //this line gets stage info
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(wizytyScene);
        window.show();
    }
}
