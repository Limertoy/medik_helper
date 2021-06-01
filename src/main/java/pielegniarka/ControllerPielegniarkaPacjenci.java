package pielegniarka;

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
import org.hibernate.query.Query;
import sample.HibernateUtil;

public class ControllerPielegniarkaPacjenci implements Initializable {
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button, buttonGenerujRecepte, buttonGenerujRecepte1;
    @FXML
    private TableView<Pacjent> table;
    @FXML
    private TableColumn nazwisko_table, imie_table, pesel_table, ulica_table, miejscowosc_table;

    public static int id_zmien = 0;

    Session session = HibernateUtil.getSessionFactory().openSession();

    public void wybierz(MouseEvent mouseEvent) { buttonGenerujRecepte.setVisible(true); buttonGenerujRecepte1.setVisible(true); }


    public void pokaz(ActionEvent actionEvent) throws IOException{

        Pacjent pacjent = table.getSelectionModel().getSelectedItem();
        id_zmien = pacjent.getId_pacjenta();

        URL url = getClass().getClassLoader().getResource("pielegniarka/generujRecepte.fxml");
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
        URL url = getClass().getClassLoader().getResource("sample.fxml");
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        //this line gets stage info
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void rejestracja(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("pielegniarka/wydawanieLekow.fxml");
        Parent rejestracjaParent = FXMLLoader.load(url);
        Scene rejestracjaScene = new Scene(rejestracjaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(rejestracjaScene);
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

    public void pokaz2(ActionEvent actionEvent) throws IOException {

        Pacjent pacjent = table.getSelectionModel().getSelectedItem();
        id_zmien = pacjent.getId_pacjenta();

        URL url = getClass().getClassLoader().getResource("pielegniarka/wydawanieLekow.fxml");
        Parent rejestracjaParent = FXMLLoader.load(url);
        Scene rejestracjaScene = new Scene(rejestracjaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(rejestracjaScene);
        window.show();
    }
}
