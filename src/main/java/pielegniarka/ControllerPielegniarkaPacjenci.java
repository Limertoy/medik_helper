package pielegniarka;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerPielegniarkaPacjenci {
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button, buttonGenerujRecepte;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> nazwisko_table, imie_table, pesel_table, ulica_table, miejscowosc_table;

    public void wybierz(MouseEvent mouseEvent) { buttonGenerujRecepte.setVisible(true); }

    public void pokaz(ActionEvent actionEvent) throws IOException{
        URL url = Paths.get("./src/main/java/pielegniarka/generujRecepte.fxml").toUri().toURL();
        Parent pokazParent = FXMLLoader.load(url);
        Scene pokazScene = new Scene(pokazParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pokazScene);
        window.show();
    }

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.setIconified(true);
    }

    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wyloguj(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/sample/sample.fxml").toUri().toURL();
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        //this line gets stage info
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void rejestracja(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/rejestracja/rejestracja.fxml").toUri().toURL();
        Parent rejestracjaParent = FXMLLoader.load(url);
        Scene rejestracjaScene = new Scene(rejestracjaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(rejestracjaScene);
        window.show();
    }
    public void zarzadzaj(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/rejestracja/zarzadzaj.fxml").toUri().toURL();
        Parent zarzadzajParent = FXMLLoader.load(url);
        Scene zarzadzajScene = new Scene(zarzadzajParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(zarzadzajScene);
        window.show();
    }
}
