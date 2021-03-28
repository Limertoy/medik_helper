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

public class ControllerZarzadzaj {
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


    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void pacjenci(ActionEvent actionEvent) throws IOException {
        Parent pacjenciParent = FXMLLoader.load(getClass().getResource("pacjenci.fxml"));
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }

    public void zarzadzaj(ActionEvent actionEvent) throws IOException {
        Parent zarzadzajParent = FXMLLoader.load(getClass().getResource("zarzadzaj.fxml"));
        Scene zarzadzajScene = new Scene(zarzadzajParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(zarzadzajScene);
        window.show();
    }

    public void rejestracja(ActionEvent actionEvent) throws IOException {
        Parent rejestracjaParent = FXMLLoader.load(getClass().getResource("rejestracja.fxml"));
        Scene rejestracjaScene = new Scene(rejestracjaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(rejestracjaScene);
        window.show();
    }

}

