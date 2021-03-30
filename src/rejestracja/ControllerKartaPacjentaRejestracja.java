package rejestracja;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerKartaPacjentaRejestracja {
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;


    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) minimalize_button.getScene().getWindow();
        stage.setIconified(true);
    }

    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("../sample/sample.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void kartaPacjenta(ActionEvent actionEvent) throws IOException {
        Parent kartaPacjentaParent = FXMLLoader.load(getClass().getResource("../lekarz/kartaPacjenta.fxml"));
        Scene kartaPacjentaScene = new Scene(kartaPacjentaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(kartaPacjentaScene);
        window.show();
    }


}
