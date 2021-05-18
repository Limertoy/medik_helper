package lekarz;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import obiekty.Pacjent;
import org.hibernate.Session;
import sample.ControllerLogin;
import sample.HibernateUtil;

import org.hibernate.query.Query;

public class ControllerPacjenci extends ControllerLogin implements Initializable {
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button, zobaczKarte;
    @FXML
    private TableView<Pacjent> table;
    @FXML
    private TableColumn nazwisko_table, imie_table, pesel_table, ulica_table, miejscowosc_table;

    public static int id_zmien = 0;

    Session session = HibernateUtil.getSessionFactory().openSession();

    public void wybierz(MouseEvent mouseEvent) { zobaczKarte.setVisible(true); }

    public void pokaz(ActionEvent actionEvent) throws IOException{
        Pacjent pacjent = table.getSelectionModel().getSelectedItem();
        id_zmien = pacjent.getId_pacjenta();
        URL url = Paths.get("./src/main/java/lekarz/kartaPacjenta.fxml").toUri().toURL();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
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
        Pacjent pacjent = table.getSelectionModel().getSelectedItem();
        id_zmien = pacjent.getId_pacjenta();
        URL url = Paths.get("./src/main/java/lekarz/kartaPacjenta.fxml").toUri().toURL();
        Parent kartaPacjentaParent = FXMLLoader.load(url);
        Scene kartaPacjentaScene = new Scene(kartaPacjentaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(kartaPacjentaScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query<Pacjent> q = session.createQuery("from Pacjent");
        List<Pacjent> list = q.list();

        ObservableList<Pacjent> data1 = FXCollections.observableArrayList();

        int i = 0;
        for(Pacjent s : list){
            data1.add(i, s);
            i++;
        }
        nazwisko_table.setCellValueFactory(new PropertyValueFactory("nazwisko_pacjenta"));
        imie_table.setCellValueFactory(new PropertyValueFactory("imie_pacjenta"));
        pesel_table.setCellValueFactory(new PropertyValueFactory("pesel"));
        ulica_table.setCellValueFactory(new PropertyValueFactory("adres"));
        miejscowosc_table.setCellValueFactory(new PropertyValueFactory("miejscowosc"));

        table.getItems().setAll(data1);
    }

}
