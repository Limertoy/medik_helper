package rejestracja;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import obiekty.Pacjent;
import obiekty.Pracownik;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.HibernateUtil;

public class ControllerRejestracjaPacjenci implements Initializable {
    Stage primaryStage;
    @FXML
    Button buttonLogin, exit_button, minimalize_button, zobaczKarte;
    @FXML
    private TableView<Pacjent> table;
    @FXML
    private TableColumn nazwisko_table, imie_table, pesel_table, ulica_table, miejscowosc_table;
    @FXML
    public TextField szukaj;
    public static int id_zmien;
    public Pacjent pacjent;
    Session session = HibernateUtil.getSessionFactory().openSession();

    public void wybierz(MouseEvent mouseEvent) {
        zobaczKarte.setVisible(true);
        id_zmien = table.getSelectionModel().getSelectedItem().getId_pacjenta();
    }

    public void pokaz(ActionEvent actionEvent) throws IOException{
        URL url = getClass().getClassLoader().getResource("rejestracja/kartaPacjentaRejestracja.fxml");
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
        URL url = getClass().getClassLoader().getResource("sample.fxml");
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        //this line gets stage info
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void rejestracja(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("rejestracja/rejestracja.fxml");
        Parent rejestracjaParent = FXMLLoader.load(url);
        Scene rejestracjaScene = new Scene(rejestracjaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(rejestracjaScene);
        window.show();
    }
    public void zarzadzaj(ActionEvent actionEvent) throws IOException {
        URL url = getClass().getClassLoader().getResource("rejestracja/zarzadzaj.fxml");
        Parent zarzadzajParent = FXMLLoader.load(url);
        Scene zarzadzajScene = new Scene(zarzadzajParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(zarzadzajScene);
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

        FilteredList<Pacjent> filteredData = new FilteredList<>(data1, b -> true);
        szukaj.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pacjent -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (pacjent.getImie_pacjenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (pacjent.getNazwisko_pacjenta().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else return pacjent.getPesel().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<Pacjent> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

}
