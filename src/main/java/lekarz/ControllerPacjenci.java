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

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerPacjenci {
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button, zobaczKarte;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> nazwisko_table, imie_table, pesel_table, ulica_table, miejscowosc_table;

    public void wybierz(MouseEvent mouseEvent) { zobaczKarte.setVisible(true); }

    public void pokaz(ActionEvent actionEvent) throws IOException{
        Parent pokazParent = FXMLLoader.load(getClass().getResource("kartaPacjenta.fxml"));
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
    public void kartaPacjenta(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/lekarz/kartaPacjenta.fxml").toUri().toURL();
        Parent kartaPacjentaParent = FXMLLoader.load(url);
        Scene kartaPacjentaScene = new Scene(kartaPacjentaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(kartaPacjentaScene);
        window.show();
    }
}
