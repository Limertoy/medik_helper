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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import obiekty.Pacjent;
import obiekty.Pracownik;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.HibernateUtil;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerZarzadzaj implements Initializable {
    @FXML
    public TableColumn godzinaTable, ponTable, wtoTable, sroTable, czwTable, piaTable, sobTable, niedTable;
    @FXML
    public TableView<Pracownik> pracownicy;
    @FXML
    private TableView<Pacjent> pacjenci;
    @FXML
    public TableColumn imie_table, nazwisko_table,imieTable, nazwiskoTable, peselTable;
    @FXML
    public TextField szukaj, szukaj1;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;

    Session session = HibernateUtil.getSessionFactory().openSession();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query<Pracownik> q = session.createQuery("from Pracownik");
        List<Pracownik> list = q.list();

        ObservableList<Pracownik> data1 = FXCollections.observableArrayList();

        int i = 0;
        for(Pracownik s : list){
            if(s.getRola().getId_rola()== 1){
            data1.add(i,s);
            i++;
            }
        }

        imie_table.setCellValueFactory(new PropertyValueFactory("imie_pracownika"));
        nazwisko_table.setCellValueFactory(new PropertyValueFactory("nazwisko_pracownika"));


        FilteredList<Pracownik> filteredData1 = new FilteredList<>(data1, b -> true);
            szukaj.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData1.setPredicate(pracownik -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                if (pracownik.getImie_pracownika().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (pracownik.getNazwisko_pracownika().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
            });
            SortedList<Pracownik> sortedData = new SortedList<>(filteredData1);
        sortedData.comparatorProperty().bind(pracownicy.comparatorProperty());
        pracownicy.setItems(sortedData);


        Query<Pacjent> q1 = session.createQuery("from Pacjent");
        List<Pacjent> list1 = q1.list();
        ObservableList<Pacjent> data2 = FXCollections.observableArrayList();
        int j = 0;
        for(Pacjent s : list1){
                data2.add(j, s);
                j++;
            }
        imieTable.setCellValueFactory(new PropertyValueFactory("imie_pacjenta"));
        nazwiskoTable.setCellValueFactory(new PropertyValueFactory("nazwisko_pacjenta"));
        peselTable.setCellValueFactory(new PropertyValueFactory("pesel"));

        FilteredList<Pacjent> filteredData2 = new FilteredList<>(data2, b -> true);
        szukaj1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData2.setPredicate(pacjent -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (pacjent.getImie_pacjenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (pacjent.getNazwisko_pacjenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (pacjent.getPesel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Pacjent> sortedData1 = new SortedList<>(filteredData2);
        sortedData1.comparatorProperty().bind(pacjenci.comparatorProperty());
        pacjenci.setItems(sortedData1);

    }


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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        URL url = Paths.get("./src/main/java/sample/sample.fxml").toUri().toURL();
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void pacjenci(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/rejestracja/rejestracjaPacjenci.fxml").toUri().toURL();
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
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

}

