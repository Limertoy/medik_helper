package lekarz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

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
        Parent loginParent = FXMLLoader.load(getClass().getResource("../sample/sample.fxml"));
        Scene loginScene = new Scene(loginParent);

        //this line gets stage info
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void wizyty(ActionEvent actionEvent) throws IOException {
        Parent wizytyParent = FXMLLoader.load(getClass().getResource("wizyty.fxml"));
        Scene wizytyScene = new Scene(wizytyParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(wizytyScene);
        window.show();
    }
    public void kalendarz(ActionEvent actionEvent) throws IOException {
        Parent kalendarzParent = FXMLLoader.load(getClass().getResource("kalendarz.fxml"));
        Scene kalendarzScene = new Scene(kalendarzParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(kalendarzScene);
        window.show();
    }
    public void kartaPacjenta(ActionEvent actionEvent) throws IOException {
        Parent kartaPacjentaParent = FXMLLoader.load(getClass().getResource("kartaPacjenta.fxml"));
        Scene kartaPacjentaScene = new Scene(kartaPacjentaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(kartaPacjentaScene);
        window.show();
    }
}
