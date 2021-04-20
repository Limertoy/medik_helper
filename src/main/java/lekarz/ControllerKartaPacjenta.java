package lekarz;

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

import javafx.stage.Stage;

public class ControllerKartaPacjenta {
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
        URL url = Paths.get("./src/main/java/sample/sample.fxml").toUri().toURL();
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void wizyty(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/lekarz/wizyty.fxml").toUri().toURL();
        Parent wizytyParent = FXMLLoader.load(url);
        Scene wizytyScene = new Scene(wizytyParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(wizytyScene);
        window.show();
    }

    public void kalendarz(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/lekarz/kalendarz.fxml").toUri().toURL();
        Parent kalendarzParent = FXMLLoader.load(url);
        Scene kalendarzScene = new Scene(kalendarzParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(kalendarzScene);
        window.show();
    }

    public void pacjenci(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/lekarz/pacjenci.fxml").toUri().toURL();
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }
    public void skierowanie(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/lekarz/skierowanie.fxml").toUri().toURL();
        Parent skierowanieParent = FXMLLoader.load(url);
        Scene skierowanieScene = new Scene(skierowanieParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(skierowanieScene);
        window.show();
    }

    public void choroby(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/lekarz/choroby.fxml").toUri().toURL();
        Parent chorobyParent = FXMLLoader.load(url);
        Scene chorobyScene = new Scene(chorobyParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(chorobyScene);
        window.show();
    }
    public void kartaPacjentaRejestracja(ActionEvent actionEvent) throws IOException {
        Parent kartaPacjentaRejestracjaParent = FXMLLoader.load(getClass().getResource("../rejestracja/kartaPacjentaRejestracja.fxml"));
        Scene kartaPacjentaRejestracjaScene = new Scene(kartaPacjentaRejestracjaParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(kartaPacjentaRejestracjaScene);
        window.show();
    }
}
